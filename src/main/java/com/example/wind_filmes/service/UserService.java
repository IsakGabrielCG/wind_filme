package com.example.wind_filmes.service;

import com.example.wind_filmes.dto.request.RegisterUserRequest;
import com.example.wind_filmes.dto.response.UserResponse;
import com.example.wind_filmes.entity.Role;
import com.example.wind_filmes.entity.User;
import com.example.wind_filmes.exception.EmailAlreadyUsedException;
import com.example.wind_filmes.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterUserRequest req) {
        String email = normalizeEmail(req.email());
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyUsedException(email);
        }

        User user = new User();
        user.setName(req.name().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(Role.USER);
        user.setActive(true);

        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    /** Lista somente usuários ATIVOS (soft delete) */
    public Page<UserResponse> list(Pageable pageable) {
        return userRepository.findAllByActiveTrue(pageable)
                .map(this::toResponse);
    }

    /** Busca um usuário ATIVO por id */
    public UserResponse get(Long id) {
        User u = userRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("User %d not found".formatted(id)));
        return toResponse(u);
    }

    /** Atualiza somente o nome (em usuário ATIVO) */
    public UserResponse updateName(Long id, String name) {
        User u = userRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("User %d not found".formatted(id)));
        u.setName(name.trim());
        return toResponse(userRepository.save(u));
    }

    /** Soft delete: marca active=false (não remove do banco) */
    public void deactivate(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User %d not found".formatted(id)));
        if (!u.isActive()) return; // já desativado
        u.setActive(false);
        userRepository.save(u);
    }

    /** Útil para /api/users/me (pegar pelo e-mail do token) */
    public User findActiveByEmail(String email) {
        return userRepository.findByEmail(normalizeEmail(email))
                .filter(User::isActive)
                .orElseThrow(() -> new EntityNotFoundException("User with email %s not found".formatted(email)));
    }

    private String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(
                u.getId(),
                u.getName(),
                u.getEmail(),
                u.getRole().name()
        );
    }
}

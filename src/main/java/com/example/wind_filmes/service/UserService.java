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

    public UserResponse register(RegisterUserRequest req){
        if (userRepository.existsByEmail(req.email())){
            throw new EmailAlreadyUsedException(req.email());
        }

        User user = new User();
        user.setName(req.name().trim());
        user.setEmail(req.email().toLowerCase());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setRole(Role.USER);

        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole().name());
    }


    public Page<UserResponse> list(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toResponse);
    }

    public UserResponse get(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
        return toResponse(u);
    }

    public UserResponse updateName(Long id, String name) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
        u.setName(name.trim());
        User saved = userRepository.save(u);
        return toResponse(saved);
    }

    public void deactivate(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found"));
        userRepository.delete(u); // por enquanto hard delete
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

package com.example.wind_filmes.service;

import com.example.wind_filmes.dto.request.RegisterUserRequest;
import com.example.wind_filmes.dto.response.UserResponse;
import com.example.wind_filmes.entity.Role;
import com.example.wind_filmes.entity.User;
import com.example.wind_filmes.exception.EmailAlreadyUsedException;
import com.example.wind_filmes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

}

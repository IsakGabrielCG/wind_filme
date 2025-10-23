package com.example.wind_filmes.controller;


import com.example.wind_filmes.dto.request.LoginRequest;
import com.example.wind_filmes.dto.request.RegisterUserRequest;
import com.example.wind_filmes.dto.response.LoginResponse;
import com.example.wind_filmes.dto.response.UserResponse;
import com.example.wind_filmes.exception.InvalidCredentialsException;
import com.example.wind_filmes.repository.UserRepository;
import com.example.wind_filmes.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody RegisterUserRequest req) {
        var created = userService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        var email = request.email().trim().toLowerCase();
        var raw   = request.password().trim();

        var user = userRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(raw, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        var resp = new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
        return ResponseEntity.ok(resp);
    }

}

package com.example.wind_filmes.controller;


import com.example.wind_filmes.dto.request.RegisterUserRequest;
import com.example.wind_filmes.dto.response.UserResponse;
import com.example.wind_filmes.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody @Valid RegisterUserRequest req) {
        UserResponse created = userService.register(req);
        return ResponseEntity.status(201).body(created);
    }
}

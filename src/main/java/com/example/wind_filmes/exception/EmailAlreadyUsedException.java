package com.example.wind_filmes.exception;

public class EmailAlreadyUsedException extends RuntimeException{
    public EmailAlreadyUsedException(String email) {
        super("Email already used: " + email);
    }
}

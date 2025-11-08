package com.example.wind_filmes.service;

import com.example.wind_filmes.entity.Movie;
import com.example.wind_filmes.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    /**
     * Retorna todos os filmes.
     * @return Lista de todos os filmes.
     */
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Retorna um filme pelo seu ID.
     * @param id O ID do filme.
     * @return Um Optional contendo o filme, ou vazio se não encontrado.
     */
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }


    /**
     * Retorna todos os filmes de uma categoria específica.
     * @param categoryId O ID da categoria.
     * @return Lista de filmes da categoria.
     */
    public List<Movie> findByCategoryId(Long categoryId) {
        return movieRepository.findByCategory_Id(categoryId);
    }

    /**
     * Salva um novo filme.
     * @param movie O objeto Movie a ser salvo.
     * @return O objeto Movie salvo.
     */
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

}
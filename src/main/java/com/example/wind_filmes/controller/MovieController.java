package com.example.wind_filmes.controller;

import com.example.wind_filmes.entity.Movie;
import com.example.wind_filmes.service.CategoryService;
import com.example.wind_filmes.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    /**
     * Rota: GET /api/movies
     * Retorna a lista de todos os filmes.
     */
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies);
    }

    /**
     * Rota: GET /api/movies/{id}
     * Retorna um filme específico pelo seu ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    /**
     * Rota: GET /api/movies/category/{id}
     * Retorna a lista de filmes de uma categoria específica.
     */
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Movie>> getMoviesByCategory(@PathVariable Long id) {
        // Verifica se a categoria existe antes de buscar os filmes
        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Movie> movies = movieService.findByCategoryId(id);
        return ResponseEntity.ok(movies);
    }
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        boolean deleted = movieService.softDelete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Rota: PUT /api/movies/{id}
     * Atualiza todos os dados de um filme existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        // Garante que o ID do filme no corpo da requisição corresponda ao ID do path
        movie.setId(id);

        return movieService.update(movie)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
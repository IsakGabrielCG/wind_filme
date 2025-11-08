package com.example.wind_filmes.controller;

import com.example.wind_filmes.entity.Category;
import com.example.wind_filmes.entity.Movie;
import com.example.wind_filmes.service.CategoryService;
import com.example.wind_filmes.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final MovieService movieService;

    /**
     * Rota: GET /api/categories
     * Retorna a lista de todas as categorias.
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    /**
     * Rota: GET /api/categories/{id}/movies
     * Retorna a lista de filmes de uma categoria específica.
     */
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<Movie>> getMoviesByCategory(@PathVariable Long id) {
        // Verifica se a categoria existe antes de buscar os filmes
        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Movie> movies = movieService.findByCategoryId(id);
        return ResponseEntity.ok(movies);
    }
}
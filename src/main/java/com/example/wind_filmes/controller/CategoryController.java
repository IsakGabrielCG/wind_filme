package com.example.wind_filmes.controller;

import com.example.wind_filmes.entity.Category;
import com.example.wind_filmes.entity.Movie;
import com.example.wind_filmes.service.CategoryService;
import com.example.wind_filmes.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final MovieService movieService;

    // CREATE
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category created = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ BY ID (novo)
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(
            @PathVariable Long id,
            @RequestBody Category category
    ) {
        Category updated = categoryService.update(id, category);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

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
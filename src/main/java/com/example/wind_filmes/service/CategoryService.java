package com.example.wind_filmes.service;

import com.example.wind_filmes.entity.Category;
import com.example.wind_filmes.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Retorna todas as categorias.
     * @return Lista de todas as categorias.
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Retorna uma categoria pelo seu ID.
     * @param id O ID da categoria.
     * @return Um Optional contendo a categoria, ou vazio se não encontrada.
     */
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    // CREATE
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    // UPDATE
    public Category update(Long id, Category data) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        existing.setName(data.getName());
        // se tiver mais campos no futuro, atualiza aqui também

        return categoryRepository.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        categoryRepository.deleteById(id);
    }
}
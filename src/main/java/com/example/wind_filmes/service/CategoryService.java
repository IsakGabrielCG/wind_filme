package com.example.wind_filmes.service;

import com.example.wind_filmes.entity.Category;
import com.example.wind_filmes.repository.CategoryRepository;
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
}
package com.example.wind_filmes.repository;

import com.example.wind_filmes.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Assumindo que a classe Movie existe no pacote entity
// e que o ID é do tipo Long, conforme visto anteriormente.
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByCategory_Id(Long categoryId);
    // Métodos de consulta padrão do Spring Data JPA


}


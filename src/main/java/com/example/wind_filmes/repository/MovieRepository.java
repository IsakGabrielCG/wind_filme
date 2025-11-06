package com.example.wind_filmes.repository;

import com.example.wind_filmes.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Assumindo que a classe Movie existe no pacote entity
// e que o ID é do tipo Long, conforme visto anteriormente.
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Métodos de consulta padrão do Spring Data JPA


}


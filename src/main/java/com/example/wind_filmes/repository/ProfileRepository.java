package com.example.wind_filmes.repository;

import com.example.wind_filmes.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    List<Profile> findByUserIdAndActiveTrue(Long userId);

    java.util.Optional<Profile> findByIdAndUserIdAndActiveTrue(Long id, Long userId);

}

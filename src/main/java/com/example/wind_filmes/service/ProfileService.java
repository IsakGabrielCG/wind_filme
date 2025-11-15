package com.example.wind_filmes.service;

import com.example.wind_filmes.dto.request.ProfileRequestDTO;
import com.example.wind_filmes.dto.response.ProfileResponseDTO;
import com.example.wind_filmes.entity.Profile;
import com.example.wind_filmes.entity.User;
import com.example.wind_filmes.repository.ProfileRepository;
import com.example.wind_filmes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public ProfileResponseDTO create(Long userId, ProfileRequestDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Profile profile = new Profile();
        profile.setName(dto.getName());
        profile.setLanguage(dto.getLanguage());
        profile.setKid(dto.isKid());
        profile.setUser(user);

        profile = profileRepository.save(profile);

        return toDTO(profile);
    }

    public List<ProfileResponseDTO> listByUser(Long userId) {
        return profileRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ProfileResponseDTO toDTO(Profile profile) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setLanguage(profile.getLanguage());
        dto.setKid(profile.isKid());
        dto.setUserId(profile.getUser().getId());
        return dto;
    }
}

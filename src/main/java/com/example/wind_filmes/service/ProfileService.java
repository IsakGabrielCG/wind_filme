package com.example.wind_filmes.service;

import com.example.wind_filmes.dto.request.ProfileRequest;
import com.example.wind_filmes.dto.response.ProfileResponse;
import com.example.wind_filmes.entity.Profile;
import com.example.wind_filmes.entity.User;
import com.example.wind_filmes.repository.ProfileRepository;
import com.example.wind_filmes.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public ProfileResponse create(Long userId, ProfileRequest dto) {

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

    public List<ProfileResponse> listByUser(Long userId) {
        return profileRepository.findByUserId(userId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ProfileResponse toDTO(Profile profile) {
        ProfileResponse dto = new ProfileResponse();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setLanguage(profile.getLanguage());
        dto.setKid(profile.isKid());
        dto.setUserId(profile.getUser().getId());
        return dto;
    }

    public ProfileResponse findById(Long userId, Long profileId) {
        Profile profile = findProfileByUserAndId(userId, profileId);
        return toDTO(profile);
    }

    public ProfileResponse update(Long userId, Long profileId, ProfileRequest dto) {
        Profile profile = findProfileByUserAndId(userId, profileId);

        profile.setName(dto.getName());
        profile.setLanguage(dto.getLanguage());
        profile.setKid(dto.isKid());

        profile = profileRepository.save(profile);
        return toDTO(profile);
    }

    public void delete(Long userId, Long profileId) {
        Profile profile = findProfileByUserAndId(userId, profileId);
        profileRepository.delete(profile);
    }

    // método auxiliar pra garantir que o perfil pertence ao usuário
    private Profile findProfileByUserAndId(Long userId, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Perfil não encontrado"));

        if (!profile.getUser().getId().equals(userId)) {
            // opcional: você pode lançar outra exception específica
            throw new EntityNotFoundException("Perfil não encontrado para este usuário");
        }

        return profile;
    }



}

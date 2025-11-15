package com.example.wind_filmes.controller;

import com.example.wind_filmes.dto.request.ProfileRequestDTO;
import com.example.wind_filmes.dto.response.ProfileResponseDTO;
import com.example.wind_filmes.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> create(
            @PathVariable Long userId,
            @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.ok(profileService.create(userId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> listByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(profileService.listByUser(userId));
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> findById(
            @PathVariable Long userId,
            @PathVariable Long profileId) {
        return ResponseEntity.ok(profileService.findById(userId, profileId));
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<ProfileResponseDTO> update(
            @PathVariable Long userId,
            @PathVariable Long profileId,
            @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.ok(profileService.update(userId, profileId, dto));
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId,
            @PathVariable Long profileId) {
        profileService.delete(userId, profileId);
        return ResponseEntity.noContent().build();
    }
}

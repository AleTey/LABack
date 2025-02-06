package com.backend.la.backendloveafrica.models.dtos.security;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(
    @NotBlank String username,
    @NotBlank String password) {

}

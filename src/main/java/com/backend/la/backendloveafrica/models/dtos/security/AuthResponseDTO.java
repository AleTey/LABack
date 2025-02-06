package com.backend.la.backendloveafrica.models.dtos.security;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "username", "message", "jwt", "status" })
public record AuthResponseDTO(
    String username,
    String message,
    String jwt,
    boolean status) {

}

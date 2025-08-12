package com.devsenior.cdiaz.property.service.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDto(
        @JsonProperty("access_token") String token,
        String email,
        @JsonProperty("name") String fullName) {
}

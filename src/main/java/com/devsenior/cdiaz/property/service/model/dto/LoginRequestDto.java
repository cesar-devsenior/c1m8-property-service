package com.devsenior.cdiaz.property.service.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
    @NotBlank(message = "El email es obligatorio")
    String email,
    
    @NotBlank(message = "La contraseña es obligatoria")
    String password
) {}

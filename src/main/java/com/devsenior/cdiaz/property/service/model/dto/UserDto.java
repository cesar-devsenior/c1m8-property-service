package com.devsenior.cdiaz.property.service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
    Long id,
    
    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres")
    String fullName,
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    String email,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    String password
) {}

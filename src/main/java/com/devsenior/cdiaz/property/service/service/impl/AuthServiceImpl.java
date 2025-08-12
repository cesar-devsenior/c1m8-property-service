package com.devsenior.cdiaz.property.service.service.impl;

import com.devsenior.cdiaz.property.service.exception.AuthenticationException;
import com.devsenior.cdiaz.property.service.exception.UserNotFoundException;
import com.devsenior.cdiaz.property.service.mapper.UserMapper;
import com.devsenior.cdiaz.property.service.model.dto.LoginRequestDto;
import com.devsenior.cdiaz.property.service.model.dto.LoginResponseDto;
import com.devsenior.cdiaz.property.service.model.dto.UserDto;
import com.devsenior.cdiaz.property.service.model.entity.User;
import com.devsenior.cdiaz.property.service.repository.UserRepository;
import com.devsenior.cdiaz.property.service.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        log.info("Intentando autenticar usuario con email: {}", loginRequest.email());
        
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con email: " + loginRequest.email()));
        
        // Por ahora, comparación simple de contraseñas (sin Spring Security)
        if (!user.getPassword().equals(loginRequest.password())) {
            throw new AuthenticationException("Credenciales inválidas");
        }
        
        // Generar token simple (en producción usar JWT)
        String token = UUID.randomUUID().toString();
        
        log.info("Usuario autenticado exitosamente: {}", user.getEmail());
        
        return new LoginResponseDto(token, user.getEmail(), user.getFullName());
    }
    
    @Override
    public UserDto register(UserDto userDto) {
        log.info("Registrando nuevo usuario con email: {}", userDto.email());
        
        if (userRepository.existsByEmail(userDto.email())) {
            throw new AuthenticationException("Ya existe un usuario con el email: " + userDto.email());
        }
        
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        
        log.info("Usuario registrado exitosamente con ID: {}", savedUser.getId());
        
        return userMapper.toDto(savedUser);
    }
}

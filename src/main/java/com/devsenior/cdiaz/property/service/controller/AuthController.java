package com.devsenior.cdiaz.property.service.controller;

import com.devsenior.cdiaz.property.service.model.dto.LoginRequestDto;
import com.devsenior.cdiaz.property.service.model.dto.LoginResponseDto;
import com.devsenior.cdiaz.property.service.model.dto.UserDto;
import com.devsenior.cdiaz.property.service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200") //cors()
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequest) {
        log.info("Recibida solicitud de login para email: {}", loginRequest.email());
        return authService.login(loginRequest);
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody UserDto userDto) {
        log.info("Recibida solicitud de registro para email: {}", userDto.email());
        return authService.register(userDto);
    }
}

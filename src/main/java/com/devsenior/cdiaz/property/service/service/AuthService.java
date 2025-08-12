package com.devsenior.cdiaz.property.service.service;

import com.devsenior.cdiaz.property.service.model.dto.LoginRequestDto;
import com.devsenior.cdiaz.property.service.model.dto.LoginResponseDto;
import com.devsenior.cdiaz.property.service.model.dto.UserDto;

public interface AuthService {
    
    LoginResponseDto login(LoginRequestDto loginRequest);
    
    UserDto register(UserDto userDto);
}

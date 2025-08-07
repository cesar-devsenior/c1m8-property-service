package com.devsenior.cdiaz.property.service.service;

import com.devsenior.cdiaz.property.service.model.dto.CreatePropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.PropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.UpdatePropertyDto;

import java.util.List;

public interface PropertyService {
    
    // Métodos CRUD básicos
    List<PropertyDto> findAll();
    
    PropertyDto findById(Long id);
    
    PropertyDto save(CreatePropertyDto createPropertyDto);
    
    PropertyDto update(Long id, UpdatePropertyDto updatePropertyDto);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
    
    // Método personalizado para buscar por ciudad
    List<PropertyDto> findByCity(String city);
} 
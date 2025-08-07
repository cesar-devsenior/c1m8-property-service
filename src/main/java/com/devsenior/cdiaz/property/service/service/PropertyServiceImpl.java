package com.devsenior.cdiaz.property.service.service;

import com.devsenior.cdiaz.property.service.exception.PropertyNotFoundException;
import com.devsenior.cdiaz.property.service.mapper.PropertyMapper;
import com.devsenior.cdiaz.property.service.model.dto.CreatePropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.PropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.UpdatePropertyDto;
import com.devsenior.cdiaz.property.service.model.entity.Property;
import com.devsenior.cdiaz.property.service.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;
    
    @Override
    public List<PropertyDto> findAll() {
        return propertyRepository.findAll()
                .stream()
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public PropertyDto findById(Long id) {
        return propertyRepository.findById(id)
                .map(propertyMapper::toDto)
                .orElseThrow(() -> new PropertyNotFoundException(id));
    }
    
    @Override
    public PropertyDto save(CreatePropertyDto createPropertyDto) {
        Property property = propertyMapper.toEntity(createPropertyDto);
        Property savedProperty = propertyRepository.save(property);
        return propertyMapper.toDto(savedProperty);
    }
    
    @Override
    public PropertyDto update(Long id, UpdatePropertyDto updatePropertyDto) {
        if (propertyRepository.existsById(id)) {
            Property property = propertyMapper.toEntity(updatePropertyDto);
            property.setId(id);
            Property updatedProperty = propertyRepository.save(property);
            return propertyMapper.toDto(updatedProperty);
        } else {
            throw new PropertyNotFoundException(id);
        }
    }
    
    @Override
    public void deleteById(Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
        } else {
            throw new PropertyNotFoundException(id);
        }
    }
    
    @Override
    public boolean existsById(Long id) {
        return propertyRepository.existsById(id);
    }
    
    @Override
    public List<PropertyDto> findByCity(String city) {
        return propertyRepository.findByCity(city)
                .stream()
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }
} 
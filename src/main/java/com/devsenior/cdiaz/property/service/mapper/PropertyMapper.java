package com.devsenior.cdiaz.property.service.mapper;

import com.devsenior.cdiaz.property.service.model.dto.CreatePropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.PropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.UpdatePropertyDto;
import com.devsenior.cdiaz.property.service.model.entity.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    
    PropertyDto toDto(Property property);
    
    @Mapping(target = "id", ignore = true)
    Property toEntity(CreatePropertyDto createPropertyDto);
    
    @Mapping(target = "id", ignore = true)
    Property toEntity(UpdatePropertyDto updatePropertyDto);
} 
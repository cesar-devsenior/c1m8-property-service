package com.devsenior.cdiaz.property.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    
    private Long id;
    private String address;
    private String city;
    private Double price;
    private Integer bedrooms;
    private Integer bathrooms;
    private String imageUrl;
    private String description;
} 
package com.devsenior.cdiaz.property.service.repository;

import com.devsenior.cdiaz.property.service.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    // Método para buscar propiedades por ciudad
    List<Property> findByCity(String city);
    
} 
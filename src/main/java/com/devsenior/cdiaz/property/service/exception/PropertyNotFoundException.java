package com.devsenior.cdiaz.property.service.exception;

public class PropertyNotFoundException extends RuntimeException {
    
    public PropertyNotFoundException(String message) {
        super(message);
    }
    
    public PropertyNotFoundException(Long id) {
        super("Propiedad no encontrada con ID: " + id);
    }
} 
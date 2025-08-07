package com.devsenior.cdiaz.property.service.controller;

import com.devsenior.cdiaz.property.service.model.dto.CreatePropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.PropertyDto;
import com.devsenior.cdiaz.property.service.model.dto.UpdatePropertyDto;
import com.devsenior.cdiaz.property.service.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de propiedades inmobiliarias.
 * 
 * <p>Este controlador proporciona endpoints para realizar operaciones CRUD
 * completas sobre las propiedades, incluyendo búsquedas específicas por ciudad
 * y validaciones de existencia.</p>
 * 
 * <p>Los endpoints siguen las convenciones REST y devuelven respuestas HTTP
 * apropiadas para cada operación. El manejo de errores se realiza a través
 * del sistema global de excepciones.</p>
 * 
 * @author DevSenior
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") //cors()
public class PropertyController {
    
    private final PropertyService propertyService;
    
    /**
     * Obtiene todas las propiedades disponibles en el sistema.
     * 
     * <p>Este endpoint retorna una lista completa de todas las propiedades
     * registradas, sin aplicar ningún filtro. La respuesta incluye todos
     * los campos de cada propiedad.</p>
     * 
     * @return Lista de todas las propiedades como {@link PropertyDto}
     * @throws Exception si ocurre un error interno en el servidor
     * 
     * @apiNote Ejemplo de respuesta:
     * <pre>
     * [
     *   {
     *     "id": 1,
     *     "address": "Calle Mayor 123",
     *     "city": "Madrid",
     *     "price": 250000.0,
     *     "bedrooms": 3,
     *     "bathrooms": 2,
     *     "imageUrl": "https://example.com/image.jpg",
     *     "description": "Hermosa casa en el centro"
     *   }
     * ]
     * </pre>
     */
    @GetMapping
    public List<PropertyDto> getAllProperties() {
        return propertyService.findAll();
    }
    
    /**
     * Obtiene una propiedad específica por su identificador único.
     * 
     * <p>Este endpoint busca una propiedad específica utilizando su ID.
     * Si la propiedad no existe, se lanza una excepción que resulta en
     * una respuesta HTTP 404 (Not Found).</p>
     * 
     * @param id Identificador único de la propiedad a buscar
     * @return La propiedad encontrada como {@link PropertyDto}
     * @throws com.devsenior.cdiaz.property.service.exception.PropertyNotFoundException 
     *         si la propiedad con el ID especificado no existe
     * 
     * @apiNote Ejemplo de respuesta:
     * <pre>
     * {
     *   "id": 1,
     *   "address": "Calle Mayor 123",
     *   "city": "Madrid",
     *   "price": 250000.0,
     *   "bedrooms": 3,
     *   "bathrooms": 2,
     *   "imageUrl": "https://example.com/image.jpg",
     *   "description": "Hermosa casa en el centro"
     * }
     * </pre>
     */
    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable Long id) {
        return propertyService.findById(id);
    }
    
    /**
     * Busca propiedades por ciudad.
     * 
     * <p>Este endpoint retorna todas las propiedades que se encuentran
     * en la ciudad especificada. La búsqueda es case-sensitive y debe
     * coincidir exactamente con el nombre de la ciudad almacenado.</p>
     * 
     * @param city Nombre de la ciudad para filtrar las propiedades
     * @return Lista de propiedades encontradas en la ciudad especificada
     * @throws Exception si ocurre un error interno en el servidor
     * 
     * @apiNote Ejemplo de uso: GET /api/properties/city/Madrid
     * <br>Ejemplo de respuesta:
     * <pre>
     * [
     *   {
     *     "id": 1,
     *     "address": "Calle Mayor 123",
     *     "city": "Madrid",
     *     "price": 250000.0,
     *     "bedrooms": 3,
     *     "bathrooms": 2,
     *     "imageUrl": "https://example.com/image.jpg",
     *     "description": "Hermosa casa en el centro"
     *   }
     * ]
     * </pre>
     */
    @GetMapping("/city/{city}")
    public List<PropertyDto> getPropertiesByCity(@PathVariable String city) {
        return propertyService.findByCity(city);
    }
    
    /**
     * Crea una nueva propiedad en el sistema.
     * 
     * <p>Este endpoint permite crear una nueva propiedad con todos los
     * datos requeridos. El ID se genera automáticamente por el sistema.
     * La respuesta incluye la propiedad creada con su ID asignado.</p>
     * 
     * @param createPropertyDto DTO con los datos de la nueva propiedad
     * @return La propiedad creada como {@link PropertyDto} con su ID asignado
     * @throws IllegalArgumentException si los datos proporcionados son inválidos
     * @throws Exception si ocurre un error interno en el servidor
     * 
     * @apiNote Ejemplo de request body:
     * <pre>
     * {
     *   "address": "Calle Mayor 123",
     *   "city": "Madrid",
     *   "price": 250000.0,
     *   "bedrooms": 3,
     *   "bathrooms": 2,
     *   "imageUrl": "https://example.com/image.jpg",
     *   "description": "Hermosa casa en el centro"
     * }
     * </pre>
     * 
     * @apiNote Código de respuesta: 201 Created
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyDto createProperty(@RequestBody CreatePropertyDto createPropertyDto) {
        return propertyService.save(createPropertyDto);
    }
    
    /**
     * Actualiza una propiedad existente por su identificador.
     * 
     * <p>Este endpoint permite actualizar todos los campos de una propiedad
     * existente. Si la propiedad no existe, se lanza una excepción que
     * resulta en una respuesta HTTP 404 (Not Found).</p>
     * 
     * @param id Identificador único de la propiedad a actualizar
     * @param updatePropertyDto DTO con los nuevos datos de la propiedad
     * @return La propiedad actualizada como {@link PropertyDto}
     * @throws com.devsenior.cdiaz.property.service.exception.PropertyNotFoundException 
     *         si la propiedad con el ID especificado no existe
     * @throws IllegalArgumentException si los datos proporcionados son inválidos
     * 
     * @apiNote Ejemplo de request body:
     * <pre>
     * {
     *   "address": "Calle Mayor 123",
     *   "city": "Madrid",
     *   "price": 260000.0,
     *   "bedrooms": 3,
     *   "bathrooms": 2,
     *   "imageUrl": "https://example.com/image.jpg",
     *   "description": "Hermosa casa en el centro - PRECIO ACTUALIZADO"
     * }
     * </pre>
     */
    @PutMapping("/{id}")
    public PropertyDto updateProperty(@PathVariable Long id, @RequestBody UpdatePropertyDto updatePropertyDto) {
        return propertyService.update(id, updatePropertyDto);
    }
    
    /**
     * Elimina una propiedad del sistema por su identificador.
     * 
     * <p>Este endpoint elimina permanentemente una propiedad del sistema.
     * Si la propiedad no existe, se lanza una excepción que resulta en
     * una respuesta HTTP 404 (Not Found).</p>
     * 
     * @param id Identificador único de la propiedad a eliminar
     * @throws com.devsenior.cdiaz.property.service.exception.PropertyNotFoundException 
     *         si la propiedad con el ID especificado no existe
     * 
     * @apiNote Código de respuesta: 204 No Content
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteById(id);
    }
    
    /**
     * Verifica si existe una propiedad con el identificador especificado.
     * 
     * <p>Este endpoint permite verificar la existencia de una propiedad
     * sin necesidad de cargar todos sus datos. Es útil para validaciones
     * previas a operaciones de actualización o eliminación.</p>
     * 
     * @param id Identificador único de la propiedad a verificar
     * @return {@code true} si la propiedad existe, {@code false} en caso contrario
     * 
     * @apiNote Ejemplo de uso: GET /api/properties/exists/1
     * <br>Ejemplo de respuesta: true
     */
    @GetMapping("/exists/{id}")
    public boolean existsById(@PathVariable Long id) {
        return propertyService.existsById(id);
    }
} 
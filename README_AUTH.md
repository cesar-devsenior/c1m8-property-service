# Sistema de Autenticación - Property Service

## Descripción
Este módulo implementa un sistema básico de autenticación para el servicio de propiedades, incluyendo login y registro de usuarios.

## Características
- ✅ Login de usuarios con email y contraseña
- ✅ Registro de nuevos usuarios
- ✅ Validación de datos con Bean Validation
- ✅ Manejo global de excepciones con @ControllerAdvice
- ✅ Mapeo de entidades con MapStruct
- ✅ Uso de Java Records para DTOs
- ✅ Logging con SLF4J y Lombok

## Endpoints

### POST /api/auth/login
**Autenticación de usuario**

**Request Body:**
```json
{
  "email": "usuario@example.com",
  "password": "contraseña123"
}
```

**Response (200 OK):**
```json
{
  "token": "uuid-generado",
  "email": "usuario@example.com",
  "fullName": "Nombre Completo"
}
```

### POST /api/auth/register
**Registro de nuevo usuario**

**Request Body:**
```json
{
  "fullName": "Nombre Completo",
  "email": "nuevo@example.com",
  "password": "contraseña123"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "fullName": "Nombre Completo",
  "email": "nuevo@example.com"
}
```

## Estructura del Proyecto

```
src/main/java/com/devsenior/cdiaz/property/service/
├── controller/
│   └── AuthController.java          # Controlador REST
├── service/
│   ├── AuthService.java             # Interfaz del servicio
│   └── impl/
│       └── AuthServiceImpl.java     # Implementación del servicio
├── repository/
│   └── UserRepository.java          # Repositorio JPA
├── model/
│   ├── entity/
│   │   └── User.java               # Entidad JPA
│   └── dto/
│       ├── LoginRequestDto.java     # DTO de solicitud de login
│       ├── LoginResponseDto.java    # DTO de respuesta de login
│       └── UserDto.java            # DTO de usuario
├── mapper/
│   └── UserMapper.java             # Mapper MapStruct
└── exception/
    ├── AuthenticationException.java # Excepción de autenticación
    ├── UserNotFoundException.java   # Excepción de usuario no encontrado
    ├── ErrorResponse.java          # Modelo de respuesta de error
    └── GlobalExceptionHandler.java # Manejador global de excepciones
```

## Base de Datos

### Tabla: users
- `id`: BIGSERIAL PRIMARY KEY
- `full_name`: VARCHAR(100) NOT NULL
- `email`: VARCHAR(100) NOT NULL UNIQUE
- `password`: VARCHAR(255) NOT NULL
- `created_at`: TIMESTAMP NOT NULL
- `updated_at`: TIMESTAMP NOT NULL

### Script de Creación
El archivo `src/main/resources/db/migration/V1__create_users_table.sql` contiene el script para crear la tabla.

## Usuario de Prueba
Se crea automáticamente un usuario de prueba:
- **Email:** demo@example.com
- **Contraseña:** password123
- **Nombre:** Usuario Demo

## Notas Importantes

⚠️ **Seguridad:** Este es un sistema básico sin Spring Security. Las contraseñas se almacenan en texto plano y la autenticación es simple.

🔒 **En Producción:** 
- Implementar Spring Security
- Usar BCrypt para hashear contraseñas
- Implementar JWT para tokens
- Agregar rate limiting
- Implementar auditoría de login

## Próximos Pasos
1. Activar Spring Security
2. Implementar JWT
3. Agregar roles y permisos
4. Implementar refresh tokens
5. Agregar validación de contraseñas robusta

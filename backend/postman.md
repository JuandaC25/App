# Pruebas Postman para Gestión de Usuarios

Este documento contiene ejemplos de pruebas para la gestión de usuarios utilizando Postman. Incluye los endpoints principales para crear, consultar, actualizar, eliminar y autenticación de usuarios.

---

## 1. Registro de Usuario

**Método:** POST  
**URL:** http://localhost:8080/api/users/register  
**Body (JSON):**
```json
{
  "username": "nuevoUsuario",
  "email": "usuario@email.com",
  "password": "123456",
  "nombre": "Juan",
  "apellido": "Pérez"
}
```

---

## 2. Autenticación de Usuario (Login)

**Método:** POST  
**URL:** http://localhost:8080/api/auth/login  
**Body (JSON):**
```json
{
  "username": "nuevoUsuario",
  "password": "123456"
}
```

---

## 3. Consultar Todos los Usuarios

**Método:** GET  
**URL:** http://localhost:8080/api/users  
**Headers:**
- Authorization: Bearer {token}

---

## 4. Consultar Usuario por ID

**Método:** GET  
**URL:** http://localhost:8080/api/users/{id}  
**Headers:**
- Authorization: Bearer {token}

---

## 5. Actualizar Usuario

**Método:** PUT  
**URL:** http://localhost:8080/api/users/{id}  
**Headers:**
- Authorization: Bearer {token}
**Body (JSON):**
```json
{
  "email": "nuevo@email.com",
  "nombre": "Juan Carlos",
  "apellido": "Pérez"
}
```

---

## 6. Eliminar Usuario

**Método:** DELETE  
**URL:** http://localhost:8080/api/users/{id}  
**Headers:**
- Authorization: Bearer {token}

---

## 7. Cambiar Contraseña

**Método:** PUT  
**URL:** http://localhost:8080/api/users/{id}/password  
**Headers:**
- Authorization: Bearer {token}
**Body (JSON):**
```json
{
  "oldPassword": "123456",
  "newPassword": "654321"
}
```

---

## Notas
- Reemplaza `{id}` por el ID real del usuario.
- Reemplaza `{token}` por el JWT obtenido en el login.
- Ajusta los campos según la estructura real de tu API.

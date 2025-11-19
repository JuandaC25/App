# Pruebas Postman para el Backend

## Conexión a la base de datos (MySQL)
- **Host:** `localhost`
- **Puerto:** `3306`
- **Base de datos:** `app_mobile_db`
- **Usuario:** `root`
- **Contraseña:** *(vacía por defecto en XAMPP)*

---

## Autenticación
**Login**
- **POST** `/api/auth/login`
- **Body:**  
  ```json
  {
    "username": "usuario",
    "password": "contraseña"
  }
  ```
- **Response:** JWT y datos de usuario

---

## Usuarios
- **GET** `/api/users` — Obtener todos los usuarios
- **GET** `/api/users/{id}` — Obtener usuario por ID
- **POST** `/api/users` — Crear usuario
  ```json
  {
    "username": "nuevoUsuario",
    "email": "usuario@email.com",
    "password": "123456",
    "role": "COORDINADOR",
    "active": true
  }
  ```
- **PUT** `/api/users/{id}` — Actualizar usuario
  ```json
  {
    "username": "usuarioActualizado",
    "email": "nuevo@email.com",
    "roles": ["COORDINADOR"]
  }
  ```
- **DELETE** `/api/users/{id}` — Eliminar usuario

---

## Categorías
- **GET** `/api/categories` — Listar todas
- **GET** `/api/categories/{id}` — Obtener por ID
- **POST** `/api/categories` — Crear
  ```json
  {
    "name": "Nueva Categoría",
    "description": "Descripción de la categoría",
    "active": true
  }
  ```
- **PUT** `/api/categories/{id}` — Actualizar
  ```json
  {
    "name": "Categoría Actualizada",
    "description": "Descripción actualizada",
    "active": true
  }
  ```
- **DELETE** `/api/categories/{id}` — Eliminar

---

## Subcategorías
- **GET** `/api/subcategories` — Listar todas
- **GET** `/api/subcategories/category/{categoryId}` — Por categoría
- **GET** `/api/subcategories/{id}` — Obtener por ID
- **POST** `/api/subcategories` — Crear
  ```json
  {
    "name": "Nueva Subcategoría",
    "description": "Descripción de la subcategoría",
    "active": true,
    "category": {
      "id": 1
    }
  }
  ```
- **PUT** `/api/subcategories/{id}` — Actualizar
  ```json
  {
    "name": "Subcategoría Actualizada",
    "description": "Descripción actualizada",
    "active": true,
    "category": {
      "id": 1
    }
  }
  ```
- **DELETE** `/api/subcategories/{id}` — Eliminar

---

## Productos
- **GET** `/api/products` — Listar todos
- **GET** `/api/products/category/{categoryId}` — Por categoría
- **GET** `/api/products/subcategory/{subcategoryId}` — Por subcategoría
- **GET** `/api/products/{id}` — Obtener por ID
- **POST** `/api/products` — Crear
  ```json
  {
    "name": "Nuevo Producto",
    "description": "Descripción del producto",
    "price": 99.99,
    "active": true,
    "category": {
      "id": 1
    },
    "subcategory": {
      "id": 1
    }
  }
  ```
- **PUT** `/api/products/{id}` — Actualizar
  ```json
  {
    "name": "Producto Actualizado",
    "description": "Descripción actualizada",
    "price": 120.00,
    "active": true,
    "category": {
      "id": 1
    },
    "subcategory": {
      "id": 1
    }
  }
  ```
- **DELETE** `/api/products/{id}` — Eliminar

---

## Estadísticas
- **GET** `/api/stats` — Obtener estadísticas generales

---

> Reemplaza `<token>` por el JWT válido y `{id}` por el ID correspondiente.
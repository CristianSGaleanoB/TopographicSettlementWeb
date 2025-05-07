# Sistema de Monitoreo de Asentamientos Topográficos

Este proyecto es una aplicación full-stack para gestionar y visualizar mediciones de asentamientos en topografía. Consiste en un backend Spring Boot con una base de datos MongoDB y un frontend React utilizando Tailwind CSS y Leaflet para la visualización de mapas. El sistema soporta autenticación de usuarios, gestión de proyectos, puntos de medición, cálculos de asentamientos y mapas interactivos.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Instrucciones de Configuración](#instrucciones-de-configuración)
  - [Prerrequisitos](#prerrequisitos)
  - [Configuración del Backend](#configuración-del-backend)
  - [Configuración del Frontend](#configuración-del-frontend)
  - [Configuración de MongoDB](#configuración-de-mongodb)
- [Endpoints API](#endpoints-api)
- [Uso](#uso)
- [Próximos Pasos](#próximos-pasos)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- **Autenticación de Usuarios**: Login seguro con autenticación basada en JWT.
- **Gestión de Proyectos**: Ver y filtrar proyectos con detalles como ubicación y estado.
- **Puntos de Medición**: Gestionar puntos con coordenadas geoespaciales y alturas iniciales.
- **Cálculos de Asentamientos**: Calcular asentamientos basados en datos de medición.
- **Mapas Interactivos**: Visualizar puntos de medición en un mapa usando Leaflet.
- **Interfaz Responsiva**: Construida con React y estilizada con Tailwind CSS para una interfaz moderna y amigable.

## Tecnologías

### Backend:
- Spring Boot 3.x
- Spring Data MongoDB
- Spring Security con JWT
- Lombok

### Frontend:
- React 18 (vía CDN)
- Tailwind CSS
- Leaflet para mapas
- Axios para peticiones API

### Base de Datos: 
- MongoDB (local o MongoDB Atlas)

### Herramientas:
- Maven (para compilación del backend)
- Java 17+
- Node.js (opcional, para servidor local)
- MongoDB Compass (opcional, para gestión de base de datos)

## Estructura del Proyecto

```
topography-settlement-system/
├── backend/
│   ├── src/main/java/com/topografia/asentamientos/
│   │   ├── config/              # Configuración de seguridad y JWT
│   │   ├── controller/          # Controladores API REST
│   │   ├── model/               # Clases de entidad MongoDB
│   │   ├── repository/          # Repositorios Spring Data MongoDB
│   │   ├── service/             # Autenticación y lógica de negocio
│   │   └── Application.java     # Aplicación principal Spring Boot
│   ├── pom.xml                  # Dependencias Maven
│   └── src/main/resources/
│       └── application.properties # Configuración MongoDB y seguridad
├── frontend/
│   └── index.html               # Aplicación React de página única
├── README.md                    # Este archivo
```

## Instrucciones de Configuración

### Prerrequisitos

- **Java 17+**: Instalar JDK (ej. OpenJDK u Oracle JDK).
- **Maven**: Para compilar el backend.
- **MongoDB**: Instalación local o cuenta MongoDB Atlas.
- **Node.js** (opcional): Para ejecutar un servidor local para el frontend.
- **IDE**: Se recomienda IntelliJ IDEA, Eclipse o VS Code.
- **Navegador**: Navegador moderno (Chrome, Firefox, etc.) para el frontend.

### Configuración del Backend

1. **Clonar o Crear el Proyecto**:

   - Si se proporciona un archivo ZIP, descomprimirlo para acceder al código del backend.
   - Alternativamente, crear un nuevo proyecto Spring Boot con las siguientes dependencias en pom.xml:
   ```xml
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-mongodb</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-security</artifactId>
       </dependency>
       <dependency>
           <groupId>io.jsonwebtoken</groupId>
           <artifactId>jjwt</artifactId>
           <version>0.9.1</version>
       </dependency>
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <optional>true</optional>
       </dependency>
   </dependencies>
   ```

2. **Configurar MongoDB**:

   - Actualizar src/main/resources/application.properties:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/topografiaDB
   spring.security.user.name=admin
   spring.security.user.password=admin
   ```

3. **Ejecutar el Backend**:

   - Navegar al directorio backend/
   - Compilar y ejecutar:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   - La API estará disponible en http://localhost:8080.

### Configuración del Frontend

1. **Crear el Archivo Frontend**:

   - Guardar el index.html proporcionado en un directorio frontend/
   - Asegurarse de que incluya React, Tailwind CSS, Leaflet y Axios vía CDNs.

2. **Servir el Frontend**:

   - Instalar un servidor HTTP simple (si no está ya instalado):
   ```bash
   npm install -g serve
   ```

   - Navegar al directorio frontend/ y ejecutar:
   ```bash
   serve
   ```

   - Acceder al frontend en http://localhost:3000 (o el puerto proporcionado por serve).

3. **Verificar Conectividad**:

   - Asegurarse de que el backend esté ejecutándose en http://localhost:8080.
   - El frontend realizará peticiones API a esta dirección.

### Configuración de MongoDB

1. **Instalar MongoDB**:

   - Descargar e instalar MongoDB localmente o usar MongoDB Atlas.
   - Iniciar el servidor MongoDB:
   ```bash
   mongod
   ```

2. **Crear la Base de Datos**:

   - Abrir un shell de MongoDB o usar MongoDB Compass.
   - Crear la base de datos topografiaDB:
   ```js
   use topografiaDB
   ```

3. **Crear Índices**:

   - Añadir índices geoespaciales para consultas basadas en ubicación:
   ```js
   db.proyectos.createIndex({ ubicacion: "2dsphere" });
   db.puntosMedicion.createIndex({ coordenadas: "2dsphere" });
   ```

4. **Insertar Datos de Prueba**:

   - Añadir un usuario de prueba (la contraseña debe estar hasheada con BCrypt):
   ```js
   db.usuarios.insertOne({
     email: "admin@topo.com",
     password: "$2a$10$EjemploHashPassword", // Reemplazar con un hash BCrypt
     rol: "ADMIN",
     nombre: "Admin"
   });
   ```

   - Añadir un proyecto de prueba:
   ```js
   db.proyectos.insertOne({
     nombre: "Construcción Edificio Central",
     descripcion: "Monitoreo de asentamientos",
     ubicacion: { type: "Point", coordinates: [-74.0059, 40.7128] },
     fecha_inicio: ISODate("2025-01-01"),
     fecha_fin: null,
     estado: "activo"
   });
   ```

   - Añadir un punto de medición de prueba:
   ```js
   db.puntosMedicion.insertOne({
     proyecto_id: ObjectId("INSERT_PROJECT_ID"),
     identificador: "Punto_A1",
     coordenadas: { type: "Point", coordinates: [-74.0060, 40.7129] },
     descripcion: "Esquina noroeste",
     altura_inicial: 100.5
   });
   ```

   - Añadir una medición de prueba:
   ```js
   db.mediciones.insertOne({
     punto_id: ObjectId("INSERT_PUNTO_ID"),
     proyecto_id: ObjectId("INSERT_PROJECT_ID"),
     fecha: ISODate("2025-05-06"),
     altura: 100.48,
     desplazamiento: 0.02,
     condiciones_ambientales: { temperatura: 22.5, humedad: 60, notas: "Cielo despejado" },
     instrumento: "Nivel óptico Leica NA730"
   });
   ```

## Endpoints API

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | /api/auth/login | Autenticar usuario y devolver JWT | Ninguna |
| GET | /api/proyectos | Listar todos los proyectos | JWT |
| GET | /api/proyectos/{id} | Obtener detalles del proyecto | JWT |
| GET | /api/proyectos/{id}/puntos | Listar puntos de un proyecto | JWT |
| GET | /api/puntos/{id}/mediciones | Listar mediciones de un punto | JWT |
| GET | /api/puntos/{id}/asentamiento | Calcular asentamiento para un punto | JWT |

## Uso

1. **Iniciar el Backend**:
   - Ejecutar la aplicación Spring Boot (`mvn spring-boot:run`).

2. **Iniciar el Frontend**:
   - Servir el archivo index.html usando `serve` u otro servidor HTTP.

3. **Acceder a la Aplicación**:
   - Abrir el navegador y navegar a http://localhost:3000.
   - Iniciar sesión con las credenciales de usuario de prueba (ej. admin@topo.com).

4. **Interactuar con el Sistema**:
   - Ver proyectos, seleccionar un proyecto para ver sus puntos en un mapa.
   - Hacer clic en un punto para ver mediciones y asentamientos calculados.
   - Cerrar sesión para volver a la pantalla de login.

## Próximos Pasos

1. **Pruebas**: Usar Postman para probar endpoints API.
2. **Mejoras**:
   - Añadir filtros para proyectos y mediciones (ej. por fecha o estado).
   - Implementar operaciones CRUD para crear/editar proyectos y puntos.
   - Añadir gráficos (ej. con Chart.js) para visualizar tendencias de medición.
   - Soportar subida de archivos para fotos de puntos o informes.

3. **Despliegue**:
   - Backend: Desplegar en Heroku, AWS o Azure.
   - Frontend: Alojar en Netlify, Vercel o GitHub Pages.

4. **Seguridad**:
   - Configurar CORS para restringir acceso API al dominio frontend.
   - Usar variables de entorno para datos sensibles (secreto JWT, URI MongoDB).

## Contribuciones

¡Las contribuciones son bienvenidas! Para contribuir:

1. Hacer fork del repositorio.
2. Crear una rama de característica (`git checkout -b nombre-caracteristica`).
3. Confirmar cambios (`git commit -m "Añadir característica"`).
4. Empujar a la rama (`git push origin nombre-caracteristica`).
5. Abrir un pull request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Ver el archivo LICENSE para detalles.

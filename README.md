# Pricing Service - Product Tariff API

API REST desarrollada en Java 17 con Spring Boot para consultar precios aplicables de productos según reglas de negocio.

## Tabla de Contenidos
1. [Descripción](#1-descripción)
2. [Tecnologías](#2-tecnologías-y-herramientas)
3. [Instalación](#3-instalación)
4. [Ejecución](#4-ejecución)
5. [Endpoints](#5-endpoints)
6. [Arquitectura](#6-arquitectura-hexagonal)
7. [Documentación](#7-documentación-openapi)
8. [Pruebas](#8-tests)
9. [Contribuciones](#9-contribuciones)
10. [Despliegue en Google Cloud](#10-despliegue-en-google-cloud)
11. [Colecciones de Postman](#11-colecciones-de-postman)
12. [Autor](#12-autor)


## 1. Descripción
Permite consultar el precio aplicable de un producto en una fecha y hora determinada, considerando reglas de prioridad y validez de tarifas.

## 2. Tecnologías y herramientas
- Java 17
- Spring Boot 3
- Maven
- H2 (base de datos en memoria)
- JPA (Hibernate)
- JUnit 5
- Jacoco (cobertura de tests)
- OpenAPI (OAS)

## 3. Instalación

3.1. Clona el repositorio:

```bash
git clone https://github.com/luisberru/inditex.git
cd inditex
 ```

3.2. Instala las dependencias:

```bash
mvn clean install  
```
## 4. Ejecución

Ejecuta la aplicación:
```bash
mvn spring-boot:run
```
Accede a la API en http://localhost:8080.

## 5. Endpoints

Obtener precio aplicable

`GET /prices`

Parámetros:

- applicationDate: Fecha y hora (ej: 2020-06-14T10:00:00)
- productId: ID del producto
- brandId: ID de la cadena

Ejemplo:

```bash
GET /prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1
```
Respuesta:

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.5,
  "currency": "EUR"
}
```
---

## 6. Arquitectura hexagonal

Este proyecto implementa una **arquitectura hexagonal** (también conocida como _Ports and Adapters_) para desacoplar el núcleo de negocio de los detalles técnicos como bases de datos, APIs o frameworks.

### Objetivo

Asegurar que el dominio del negocio se mantenga **independiente, testable y reutilizable**, separando claramente la lógica de negocio de los mecanismos de entrada/salida.

---

### Estructura de paquetes
```graphql
com.bcncgroup.inditex
├── application                    # Capa de aplicación (casos de uso)
│   └── service                    # Implementaciones de casos de uso
├── domain                          # Capa de dominio (núcleo puro)
│   ├── exception                  # Excepciones específicas del dominio
│   ├── model                      # Entidades y Value Objects del dominio
│   └── port                       # Puertos (interfaces) que expone o necesita el dominio
│       ├── inbound                # Interfaces que define el dominio para entrada
│       └── outbound               # Interfaces que define el dominio para salida
├── infrastructure                 # Adaptadores e infraestructura técnica
│   ├── adapter                    # Adaptadores que conectan hacia dentro o fuera
│   │   ├── inbound                 # Entradas al sistema
│   │   │   └── rest
│   │   │       ├── advice         # Manejo global de excepciones HTTP
│   │   │       ├── controller     # Controladores REST
│   │   │       ├── dto            # Objetos de transferencia Request/Response
│   │   │       └── mapper         # Conversores entre DTO y modelo de dominio
│   │   └── outbound                # Salidas del sistema
│   │       └── persistence
│   │           ├── entity        # Entidades JPA (persistencia)
│   │           ├── mapper        # Conversores entre entidad JPA y dominio
│   │           └── repository    # Implementaciones de los puertos de salida
│   └── config                     # Configuraciones técnicas de la app (Beans, OpenAPI, etc.)
└── PriceServiceApplication.java   # Clase principal de Spring Boot

```

### Flujo de ejecución

1. El adaptador inbound (por ejemplo, un REST Controller) recibe una solicitud externa.
2. El adaptador inbound invoca un puerto inbound (una interfaz de caso de uso definida en el dominio).
3. La capa de aplicación implementa este puerto a través de un servicio de aplicación, el cual coordina la lógica de negocio.
4. El servicio de aplicación opera sobre modelos del dominio y, si necesita datos externos (por ejemplo, persistencia), invoca un puerto outbound.
5. Un adaptador outbound implementa el puerto outbound y se encarga de acceder a sistemas externos (como bases de datos o APIs).
6. El resultado obtenido pasa nuevamente al servicio de aplicación.
7. El adaptador inbound transforma el resultado del dominio en un DTO adecuado para exponerlo al cliente.
8. Finalmente, se devuelve la respuesta al solicitante.

### Ventajas

- Separación clara de responsabilidades
- Dominio desacoplado de frameworks y tecnología
- Fácil de testear sin necesidad de levantar Spring
- Adaptable a múltiples canales: REST, Kafka, CLI, etc.
- Facilita el mantenimiento y evolución de la aplicación



### Testing

- El dominio se prueba con pruebas unitarias puras (sin contexto Spring)
- Los adaptadores se testean por separado mediante pruebas de integración
- La lógica de negocio es independiente del transporte, persistencia o formato


### Ejemplo de componentes clave

| Tipo                   | Clase                                | Descripción                                      |
|-------------------------|--------------------------------------|--------------------------------------------------|
| Entidad                 | `Price`                             | Modelo del dominio que representa un precio.     |
| Puerto inbound          | `GetPriceUseCase`                   | Interfaz que expone el caso de uso para ser invocado externamente. |
| Puerto outbound         | `PriceRepository`                   | Interfaz que define las operaciones de persistencia requeridas por el dominio. |
| Servicio de aplicación  | `PriceService`                      | Implementación de los casos de uso, coordinando entidades y puertos. |
| Adaptador REST          | `PriceController`                   | Expone los endpoints públicos mediante REST.     |
| Adaptador JPA           | `PriceJpaRepository` + `PriceMapper` | Implementan la persistencia de datos utilizando JPA y mapeo a dominio. |

### Buenas prácticas seguidas

- No se exponen clases de infraestructura desde el dominio
- Las entidades JPA (`PriceEntity`) no se usan fuera del adaptador de persistencia
- Cada capa tiene sus propios modelos (DTOs, entidades, modelos de dominio)
- Se usa mapeo explícito entre capas

---

## 7. Documentación OpenAPI

La API cuenta con documentación generada automáticamente utilizando **Springdoc OpenAPI**.

### Acceso a la documentación (En despliegue local)
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Especificación OpenAPI**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

Para consultar la documentación OpenAPI de la aplicación desplegada en Google Cloud, dirígete al siguiente apartado:  
[Documentación OpenAPI - Google Cloud](#documentación-openapi-google-cloud)

## 8. Tests
Cobertura: 100% del core DDD con Jacoco.

Ejecución:

```bash
mvn clean verify
```
Reporte: target/site/jacoco/index.html.

## 9. Contribuciones

Para contribuir al proyecto, sigue estos pasos:

Crea una rama para tu feature:
```bash
git checkout -b feature/nueva-funcionalidad
```
Realiza tus cambios y haz un commit:
```bash
git commit -m "Agrega nueva funcionalidad"
```
Envía un pull request.

Sigue el flujo de trabajo `GitFlow` integrando las ramas primero en `develop` y luego en `main`.

## 10. Despliegue en Google Cloud

La aplicación ha sido desplegada en Google Cloud y está disponible en la siguiente URL:

[https://price-service-java-215191314670.us-central1.run.app](https://price-service-java-215191314670.us-central1.run.app)

### Endpoints disponibles

- **Obtener precio aplicable**:  
`GET /prices`

**Parámetros**:
  - `date`: Fecha y hora (ej: 2020-06-14T10:00:00)
  - `productId`: ID del producto
  - `brandId`: ID de la cadena

**Ejemplo de solicitud**:
```bash
curl "https://price-service-java-215191314670.us-central1.run.app/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1"
  ```
**Respuesta**:
  
```json
{
"productId": 35455,
"brandId": 1,
"priceList": 1,
"startDate": "2020-06-14T00:00:00",
"endDate": "2020-12-31T23:59:59",
"price": 35.5,
"currency": "EUR"
}
```
### Documentación OpenAPI Google Cloud

La documentación de la API también está disponible en el despliegue:
Swagger UI: https://price-service-java-215191314670.us-central1.run.app/swagger-ui.html

Especificación OpenAPI: https://price-service-java-215191314670.us-central1.run.app/v3/api-docs

## 11. Colecciones de Postman

Este apartado describe cómo importar, configurar y utilizar las colecciones de Postman para probar los endpoints del servicio.

### Configuración

**Importar colección en Postman:**

1. Abre Postman.
2. Haz clic en **_Import_**.
3. Selecciona el archivo: `postman/Inditex.postman_collection.json`.

También puedes acceder directamente al archivo desde el siguiente enlace:

[postman/Inditex.postman_collection.json](postman/Inditex.postman_collection.json)


### Uso

- Si has desplegado el servicio de forma **local**, utiliza los requests:
  - `Api-docs - Local`
  - `Prices - Local`

- Si deseas probar el servicio desplegado en **Google Cloud**, utiliza los requests:
  - `Api-docs - Google Cloud`
  - `Prices - Google Cloud`


## 12. Autor

Luis Gabriel Berru Aguilar
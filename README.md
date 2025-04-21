# Pricing Service - Product Tariff API

API REST desarrollada en Java 17 con Spring Boot para consultar precios aplicables de productos según reglas de negocio.

## Tabla de Contenidos
1. [Descripción](#1-descripción)
2. [Tecnologías](#2-tecnologías-y-herramientas)
3. [Instalación](#3-instalación)
4. [Ejecución](#4-ejecución)
5. [Endpoints](#5-endpoints)
6. [Arquitectura](#6-arquitectura-hexagonal)
7. [Documentación](#7-documentacion-opeapi)
8. [Pruebas](#8-tests)
9. [Contribuciones](#9-contribuciones)
10. [Autor](#10-autor)

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

1. Clona el repositorio:

```bash
git clone https://github.com/luisberru/inditex.git
cd inditex
  ```
2. Instala las dependencias:

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
## 6. Arquitectura hexagonal

El proyecto sigue principios de arquitectura hexagonal:

- domain: Entidades y lógica de negocio.
- application: Casos de uso.
- adapter: Controladores REST.
- infrastructure: Persistencia (JPA).
- config: Configuraciones de Spring.

## 7. Documentación OpenAPI

La API cuenta con documentación generada automáticamente utilizando **Springdoc OpenAPI**.

### Acceso a la documentación
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Especificación OpenAPI**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

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

## 10. Autor

Luis Gabriel Berru Aguilar
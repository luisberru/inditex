# Pricing Service - Product Tariff API

Este proyecto es una API REST desarrollada en Java 17 con Spring Boot, siguiendo principios de arquitectura hexagonal, DDD, Clean Code y SOLID, que permite obtener el precio aplicable de un producto según una fecha, identificador de producto e identificador de cadena.

## 💡 Descripción

Permite consultar el precio aplicable de un producto en una fecha y hora determinada, considerando reglas de prioridad y validez de tarifas.

## 🌐 Tecnologías y herramientas

- Java 17

- Spring Boot 3

- Maven

- H2 (base de datos en memoria)

- JPA (Hibernate)

- JUnit 5

- Jacoco (cobertura de tests)

## ⚖️ Arquitectura hexagonal

- domain: contiene las entidades y lógica de negocio pura.

- application: contiene los casos de uso.

- adapter: controladores REST.

- infrastructure: implementaciones de persistencia (JPA).

- config: configuraciones de Spring.

## 🎯 Patrones aplicados

- DDD: entidad Price, PriceService como caso de uso.

- SOLID: principios aplicados en la separación de responsabilidades.

- Clean Code: nombres claros, métodos pequeños, sin duplicidad.

## 📁 Estructura del proyecto
```
src/main/java/com/bcncgroup/inditex
├── domain
│   └── model
├── application
│   └── service
├── adapter
│   ├── controller
│   └── dto
├── infrastructure
│   ├── repository
│   └── exception
└── config
```
## 📆 Endpoints

Obtener precio aplicable

- GET /prices

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

## ⚠️ Códigos de error HTTP

- 200 OK: Respuesta correcta

- 400 BAD REQUEST: Parámetros inválidos

- 404 NOT FOUND: No se encontró tarifa para los parámetros dados

- 500 INTERNAL SERVER ERROR: Error no controlado

## 🔒 Control de errores

`@ControllerAdvice` global con clases personalizadas para errores.

## ✅ Tests

5 tests obligatorios cubiertos:

- 14/06 a las 10:00

- 14/06 a las 16:00

- 14/06 a las 21:00

- 15/06 a las 10:00

- 16/06 a las 21:00

Test de integración incluido

100% cobertura del core DDD con Jacoco

## 🎯 Cobertura de código

Ejecutar:

```bash
mvn clean verify
```

Ver reporte: `target/site/jacoco/index.html`


## 🙋 Autor

Luis Gabriel Berru Aguilar
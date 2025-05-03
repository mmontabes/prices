# Product Price Microservice

This project is a microservice developed in Java (Spring Boot) that manages product prices for different brands and provides a REST access point for queries. It follows a hexagonal architecture and uses an in-memory H2 database for testing and temporary data storage.



## Description

The service allows querying product prices based on a date range, brand, and specific product. The prices include information such as priority, validity period, and the currency in which they are expressed.

### Features
- Data persistence in an in-memory H2 database to facilitate testing.
- REST APIs designed following hexagonal architecture principles.



## Technology stack

- Language: Java 17  
- Framework: Spring Boot  
- Database: H2 (in memory)  
- Architecture: Hexagonal  
- API Creation: OpenAPI / Swagger  
- Testing: JUnit



## Architecture

The project follows a hexagonal architecture where:

1. Domain: Contains entities, services, and business logic.  
2. Application: Manages use cases and connects requests to the domain.  
3. Infrastructure: Handles persistence and external adapters (e.g., REST controllers).


## Covered test cases

- Query at 10:00 on the 14th.  
- Query at 16:00 on the 14th.  
- Query at 21:00 on the 14th.  
- Query at 10:00 on the 15th.  
- Query at 21:00 on the 16th.



## How to Run the Project

### Prerequisites

- Java 17
- Maven 3.8+

### Steps to launch

- git clone https://github.com/mmontabes/prices.git
- cd prices
- mvn clean install
- mvn spring-boot:run


## REST API Usage

### Endpoint to query product price

```http
GET /prices?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00
```

### Query Parameters

| Parameter         | Type   | Description                              |
|------------------|--------|------------------------------------------|
| `productId`       | Long   | Product identifier                       |
| `brandId`         | Long   | Brand identifier                         |
| `applicationDate` | String | Date/time in ISO format `yyyy-MM-dd'T'HH:mm:ss` |

### Example Response

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}




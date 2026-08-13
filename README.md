# Ecommerce Engine

A Spring Boot based e-commerce backend built as a learning project to explore modern backend development concepts, including REST APIs, microservices, Redis caching, database persistence, projections, external API integration, payment gateways, and Spring Cloud service discovery.

---

## 🏗️ Architecture

The project is gradually being evolved from a simple Spring Boot application into a microservices-based e-commerce system.

```text
                         ┌─────────────────────┐
                         │       Client        │
                         │   Postman / Web UI  │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │   Product Service   │
                         │     Port: 9000      │
                         └──────┬───────┬──────┘
                                │       │
                    ┌───────────┘       └─────────────┐
                    ▼                                 ▼
             ┌─────────────┐                    ┌─────────────┐
             │    Redis    │                    │    MySQL    │
             │    Cache    │                    │  Database   │
             └─────────────┘                    └─────────────┘
                    │
                    ▼
             ┌──────────────────┐
             │   Fake Store API │
             │ External Product │
             │      Source      │
             └──────────────────┘


             ┌──────────────────┐
             │  Payment Service │
             │                  │
             │  Stripe / Razorpay
             └────────┬─────────┘
                      │
                      ▼
               Payment Gateway


             ┌──────────────────┐
             │ Service Discovery│
             │     Eureka       │
             └──────────────────┘
````

---

# 🚀 Technologies

### Backend

* Java 17
* Spring Boot 3.5.x
* Spring MVC
* Spring Data JPA
* Hibernate
* Spring Security
* Spring Data Redis
* Spring Cloud
* Netflix Eureka

### Database & Storage

* MySQL
* Redis
* Flyway

### External Services

* Fake Store API
* Stripe
* Razorpay

### Build & Development

* Maven
* IntelliJ IDEA
* Postman
* Git / GitHub

---

# 📦 Project Structure

```text
Ecommerce-engine/
│
├── ProductService/
│   │
│   ├── src/main/java/
│   │   └── com/Ashim/CommerceEngine/
│   │       │
│   │       ├── productService/
│   │       │   ├── controllers/
│   │       │   ├── services/
│   │       │   ├── repositories/
│   │       │   ├── models/
│   │       │   ├── dtos/
│   │       │   ├── projections/
│   │       │   └── exceptions/
│   │       │
│   │       └── ProductServiceApplication.java
│   │
│   └── src/main/resources/
│       ├── application.properties
│       └── db/migration/
│
├── PaymentService/
│   │
│   └── src/main/java/
│       └── com/ashim/PaymentService/
│           ├── controller/
│           ├── services/
│           ├── paymentGateway/
│           └── models/
│
├── ServiceDiscovery/
│   └── Eureka Server
│
└── README.md
```

---

# 🛍️ Product Service

The Product Service is the main e-commerce service.

It provides APIs for:

* Creating products
* Fetching products
* Updating products
* Deleting products
* Pagination
* Product summaries
* Category management
* External product integration
* Redis caching

---

# 🔌 Product API

### Get Product

```http
GET /products/{id}
```

Example:

```http
GET http://localhost:9000/products/20
```

---

### Get Product Summary

Returns only selected product fields using a JPA projection.

```http
GET /products/summary
```

Example response:

```json
[
  {
    "title": "iPhone 14",
    "price": 69999
  },
  {
    "title": "MacBook Air M3",
    "price": 114999
  }
]
```

Instead of loading the complete `Product` entity, the database returns only the required fields.

---

# 📄 Pagination

Products can be retrieved using pagination.

```http
GET /products?pageNumber=0&pageSize=10
```

Spring Data's `Pageable` and `Page` are used to implement pagination.

---

# 🗄️ Database

The application uses MySQL for persistent storage.

Main entities include:

* Product
* Category
* User
* Order
* Payment

Example relationship:

```text
Category
   │
   └─── 1 : N ─── Products
```

---

# 🔄 Database Migration

Flyway is used for database versioning.

Migration files are stored under:

```text
src/main/resources/db/migration/
```

Example:

```text
V1__create_product_tables.sql
V2__create_category_tables.sql
V3__add_indexes.sql
V4__insert_initial_data.sql
```

Flyway keeps track of which migrations have already been executed.

---

# ⚡ Redis Caching

Redis is used as a cache for product data.

The basic flow is:

```text
Client
  │
  ▼
Product Service
  │
  ▼
Check Redis
  │
  ├── Cache HIT ──────► Return Product
  │
  └── Cache MISS
          │
          ▼
      Fake Store API
          │
          ▼
      Store in Redis
          │
          ▼
      Return Product
```

For example:

```java
Product product =
        (Product) redisTemplate
                .opsForHash()
                .get("products", "products_" + productId);
```

If the product is present in Redis, the external API call can be avoided.

This significantly reduces response time.

---

# 🌐 Fake Store API Integration

The project also demonstrates integration with an external REST API.

`RestTemplate` is used to communicate with Fake Store API.

Example:

```java
FakeStoreProductDto product =
        restTemplate.getForObject(
            "https://fakestoreapi.com/products/" + productId,
            FakeStoreProductDto.class
        );
```

The application uses an abstraction:

```text
ProductService
      │
      ├── SelfProductService
      │       └── MySQL
      │
      └── FakeStoreProductService
              └── Fake Store API
```

This demonstrates programming to an interface and allows different implementations of the same business operation.

---

# 🔍 JPA Projection

The project demonstrates Spring Data JPA interface-based projections.

Example:

```java
public interface ProductWithTitleAndPrice {

    String getTitle();

    Double getPrice();
}
```

Repository:

```java
@Query("""
       SELECT p.title AS title,
              p.price AS price
       FROM Product p
       """)
List<ProductWithTitleAndPrice> findAllProjectedBy();
```

Instead of retrieving the entire entity:

```text
Product
 ├── id
 ├── title
 ├── price
 ├── description
 ├── image
 ├── category
 ├── createdAt
 └── updatedAt
```

the database returns only:

```text
title
price
```

This is useful when the client needs only a subset of fields.

---

# 💳 Payment Service

Payment processing is separated into its own service.

The Payment Service follows the Gateway/Strategy approach.

```text
PaymentController
       │
       ▼
PaymentService
       │
       ▼
PaymentGateway
       │
       ├── StripePaymentGateway
       │
       └── RazorpayPaymentGateway
```

The application does not directly depend on Stripe or Razorpay in the business layer.

Instead, it depends on:

```java
PaymentGateway
```

This makes it possible to change the payment provider without changing the core payment service.

---

# 💰 Stripe Payment Flow

The current Stripe implementation creates a Payment Link.

The flow is:

```text
Client
  │
  │  Create Order
  ▼
Order Service
  │
  │ orderId
  ▼
Payment Service
  │
  ▼
StripePaymentGateway
  │
  ├── Create Price
  │
  ├── Create Payment Link
  │
  ▼
Stripe
  │
  ▼
Payment URL
  │
  ▼
Client
```

The Stripe integration uses:

```java
StripeClient
```

A price is created:

```java
PriceCreateParams priceParams =
        PriceCreateParams.builder()
                .setCurrency("inr")
                .setUnitAmount(100000L)
                .setProductData(
                        PriceCreateParams.ProductData.builder()
                                .setName("iphone_charger")
                                .build()
                )
                .build();
```

Then:

```java
Price price = stripeClient
        .prices()
        .create(priceParams);
```

A Payment Link is then created using the generated price:

```java
PaymentLinkCreateParams paymentLinkParams =
        PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(price.getId())
                                .setQuantity(1L)
                                .build()
                )
                .build();
```

Finally:

```java
PaymentLink paymentLink =
        stripeClient.paymentLinks()
                .create(paymentLinkParams);
```

The URL is returned to the client:

```java
return paymentLink.getUrl();
```

---

# 🔐 Payment Idempotency

Payment systems must be designed carefully because clients can retry requests.

For example:

```text
POST /payments
```

could be sent twice because of:

* Network timeout
* Browser retry
* Client retry
* Load balancer retry
* Application retry

Without idempotency:

```text
Request 1 → Payment created
Request 2 → Another payment created
```

This can result in duplicate charges.

The intended design is:

```text
Client
   │
   │ orderId / idempotency key
   ▼
Payment Service
   │
   ├── Check existing payment
   │
   ├── Already processed?
   │       └── Return existing result
   │
   └── Otherwise
           │
           ▼
       Payment Gateway
```

The `orderId` is created before initiating payment and is used to associate the payment with the order.

---

# 🔔 Stripe Webhooks

Payment completion should not rely only on the browser redirect.

The payment provider can notify the backend through a webhook.

```text
Customer
   │
   ▼
Stripe Checkout
   │
   │ Payment
   ▼
Stripe
   │
   │ Webhook
   ▼
Payment Service
   │
   ▼
Verify Event
   │
   ▼
Update Payment Status
```

This allows the backend to receive the actual payment event independently of the user's browser.

---

# 🔎 Service Discovery

The project also introduces Spring Cloud Netflix Eureka.

The architecture becomes:

```text
                 ┌──────────────────┐
                 │  Eureka Server    │
                 │ Service Discovery │
                 └────────┬─────────┘
                          │
             ┌────────────┼────────────┐
             │            │            │
             ▼            ▼            ▼
      Product Service  Payment      User Service
                       Service
```

Services register themselves with Eureka.

Instead of hardcoding service locations:

```text
http://localhost:9000
http://localhost:8080
```

services can discover each other through service names.

---

# 🔐 Configuration & Secrets

Environment-specific configuration is kept outside the committed source code.

Example:

```text
DB_URL
DB_USERNAME
DB_PASSWORD

STRIPE_API_KEY

SERVER_PORT
```

The `.env` file is intentionally excluded from Git:

```gitignore
.env
```

Never commit:

* Database passwords
* Stripe secret keys
* API keys
* OAuth secrets
* JWT secrets

---

# 🧪 Running the Project

## Prerequisites

Install:

* Java 17
* Maven
* MySQL
* Redis
* Git

---

## 1. Clone

```bash
git clone https://github.com/ashim-roy/Ecommerce-engine.git
```

```bash
cd Ecommerce-engine
```

---

## 2. Configure Environment Variables

Create a local `.env` / environment configuration containing the required values.

Example:

```text
SERVER_PORT=9000

DB_URL=jdbc:mysql://localhost:3306/productservice
DB_USERNAME=<your-db-user>
DB_PASSWORD=<your-db-password>

JPA_SHOW_SQL=true
DDL_AUTO=update
FLYWAY_BASELINE_ON_MIGRATE=true
```

Do not commit this file.

---

# ▶️ Start Redis

Verify Redis:

```bash
redis-server --version
```

Test the connection:

```bash
redis-cli ping
```

Expected:

```text
PONG
```

---

# ▶️ Start Product Service

Run:

```bash
mvn spring-boot:run
```

The Product Service runs on:

```text
http://localhost:9000
```

---

# ▶️ Start Payment Service

Run the Payment Service separately.

Example:

```text
http://localhost:<payment-service-port>
```

---

# ▶️ Start Service Discovery

Start the Eureka Server separately.

Once running, services register themselves with Eureka.

---

# 🧠 Concepts Covered

This project is primarily a learning project and demonstrates several important backend engineering concepts:

### Spring Boot

* Dependency Injection
* IoC
* REST Controllers
* Service Layer
* Repository Layer
* Configuration
* Exception Handling

### Spring Data JPA

* Entity Mapping
* JpaRepository
* JPQL
* Pagination
* Projections
* DTOs
* Repository queries

### Redis

* Caching
* Cache Hit / Cache Miss
* RedisTemplate
* Redis Hashes

### Microservices

* Service separation
* Payment Service
* Product Service
* Service Discovery
* Eureka

### Payment Systems

* Payment Gateway abstraction
* Stripe integration
* Payment Links
* Webhooks
* Idempotency
* Order ID based payment tracking

### Software Design

* Programming to an Interface
* Strategy / Gateway pattern
* DTO pattern
* Projection
* Separation of concerns

---

# 📚 Learning Goals

The project is being developed incrementally to understand how a production-style e-commerce backend evolves.

The progression is roughly:

```text
Spring Boot
     ↓
REST APIs
     ↓
JPA + MySQL
     ↓
DTOs
     ↓
Pagination
     ↓
Projections
     ↓
External API Integration
     ↓
Redis Caching
     ↓
Payment Service
     ↓
Stripe Integration
     ↓
Payment Webhooks
     ↓
Idempotency
     ↓
Microservices
     ↓
Eureka Service Discovery
```

---

# 🚧 Future Improvements

Potential next steps include:

* API Gateway
* OpenFeign
* Circuit Breaker / Resilience4j
* Centralized Configuration
* Kafka
* Distributed tracing
* Centralized logging
* Docker
* Kubernetes
* CI/CD
* Order Service
* Inventory Service
* Notification Service
* Complete payment state machine
* Production-grade idempotency
* Automated integration tests

---

## 👨‍💻 Author

**Ashim Roy**

Backend Engineer | Java | Spring Boot | Microservices | Distributed Systems

---

## ⭐ Purpose

This repository is primarily a hands-on backend engineering and microservices learning project, with the goal of understanding how individual Spring Boot concepts fit together to form a scalable e-commerce system.

```

### One thing I would change before you commit this

I deliberately **didn't put your actual DB credentials, Stripe keys, or other secrets** anywhere in the README. Keep those local/environment-specific.

Also, your current project has evolved quite a bit—from the original Product Service into **Product + Payment + Service Discovery + Redis**, so this README reflects the architecture we're actually building rather than describing it as just a CRUD Spring Boot application.
```

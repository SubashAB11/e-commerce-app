# E-Commerce Microservice Application

Welcome to the E-Commerce Microservice Application! This project showcases a robust backend architecture using Spring Boot and various other technologies to create a scalable and efficient e-commerce platform.

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Services](#services)

## Introduction
This e-commerce application is built using a microservice architecture with Spring Boot. It includes various services such as customer management, product catalog, order processing, payment handling, and notifications. The application is designed to be scalable, maintainable, and easy to extend.

## Technologies Used
- **Spring Boot**: Framework for building Java-based applications.
- **REST Services**: For creating RESTful APIs.
- **MailDev**: For sending emails to customers on ordering.
- **PostgreSQL**: Relational database for storing structured data.
- **MongoDB**: NoSQL database for storing unstructured data.
- **Eureka Server**: For client discovery.
- **Config Server**: For centralized configuration management.
- **Kafka**: For message brokering and event streaming.
- **API Gateway**: For routing requests to appropriate services.
- **Docker**: For containerization of services.
- **Swagger**: For API documentation.
- **Zipkin**: For distributed tracing.
- **Keycloak**: For identity and access management.
- **Docker Compose**: For orchestrating multi-container Docker applications.

## Architecture
The application follows a microservice architecture with the following structure:
1. **API Gateway**: Entry point for all client requests.
2. **Customer Service**: Manages customer information.
3. **Product Service**: Manages product catalog.
4. **Order Service**: Handles order processing.
5. **Payment Service**: Manages payment transactions.
6. **Notification Service**: Sends notifications to customers.

## Services
- **Customer Service**: CRUD operations for customer data.
- **Product Service**: CRUD operations for product data.
- **Order Service**: Manages order lifecycle.
- **Payment Service**: Processes payments.
- **Notification Service**: Sends order confirmation and updates via email.


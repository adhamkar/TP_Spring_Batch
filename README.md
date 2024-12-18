# Banking Transfer Management System

## Table of Contents
- [Introduction](#introduction)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Microservices](#microservices)
    - [Beneficiary Service](#beneficiary-service)
    - [Transfer Service](#transfer-service)
    - [Gateway Service](#gateway-service)
    - [Discovery Service](#discovery-service)
    - [Config Service](#config-service)
- [Screen Shots](#screen-shots)

## Introduction
This project is a proof of concept for a banking transfer management system. It is built using a microservices architecture to facilitate web and mobile transactions for banking clients. The application consists of several microservices that manage beneficiaries, bank transfers, and user interactions through an AI-powered chatbot.

## Technologies Used
- **Backend:**
    - Spring Boot
    - Spring Cloud
    - OpenFeign
- **Frontend:**
    - Angular
- **Database:**
    - H2
- **Other Technologies:**
    - Docker

## Architecture
The application follows a microservices architecture with the following components:
- Beneficiary Service
- Transfer Service
- Gateway Service
- Discovery Service
- Config Service

## Microservices

### Beneficiary Service
This microservice manages beneficiaries' information, including their name, RIB, and type (physical or moral). It provides RESTful endpoints to create, read, update, and delete beneficiaries.

### Transfer Service
This service handles bank transfers, allowing users to initiate transactions. It maintains transfer records, including details such as the amount, source RIB, beneficiary ID, and transfer type (normal or instant).

### Gateway Service
This service acts as an API gateway, routing requests to the appropriate microservices and handling authentication and authorization.

### Discovery Service
The discovery service facilitates service registration and discovery within the microservices architecture, using Eureka or Consul.

### Config Service
This service manages application configuration settings for the microservices, enabling dynamic configuration updates.

## Screen Shots

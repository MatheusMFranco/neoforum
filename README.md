<div align="center">

# Neoforum

![](https://img.shields.io/badge/Framework-springboot-brightgreen)
![](https://img.shields.io/badge/docs-swagger-brightgreen)
![](https://img.shields.io/badge/K8S-Openshift-brightgreen)

[![Basic validation](https://github.com/actions/labeler/actions/workflows/basic-validation.yml/badge.svg?branch=main)](https://github.com/actions/labeler/actions/workflows/basic-validation.yml)

</div>

This API is inspired by Neoboards from Neopets. It provides endpoints to:
- Consult Boards: Retrieve information about various boards;
- Consult Topics: Access details of topics within boards;
- Consult Answers: View responses to topics and answers;
- Update Author Information: Modify author profiles and related data;
- And much more!

## Index
- [Stack](#stack)
- [Prerequisites](#prerequisites)
- [Environment](#setting-up-the-environment)
- [Build](#build-the-project)
- [Running](#running-the-application)
- [Test](#tests)
- [Documentation](#viewing-documentation)
- [Container](#using-docker)
- [Production](#accessing-the-application-in-production)


## Stack
- Spring Boot 3
- Flywaydb
- JPA
- Mysql 9
- Redis
- Junit 5
- Mockk
- Rest
- Actuator
- Prometheus

## Prerequisites
- Java 21
- Kotlin 1.9
- Maven
- Docker

## Setting Up the Environment

You need to run Docker containers for Redis and MySQL. Follow the instructions below to run each container.

**Run MySQL Container**

To start a MySQL container, run the following command:

```bash
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=neoforum -p 3306:3306 -d mysql:9
```

**Run Redis Container**

To start a Redis container, run the following command:

```bash
docker run --name redis -p 6379:6379 -d redis:latest
```

## Build the Project

To build the project, run the following Maven command:

```bash
./mvnw clean install
```

## Running the Application

To run the Spring Boot application, use the following command:

```bash
./mvnw spring-boot:run
```

## Tests

To run tests, you must use Maven as well. To execute all unit tests, run:
```bash
./mvnw test
```

## Viewing Documentation

The Swagger API documentation is available once the application is running. By default, the Swagger UI can be accessed at:
```bash
http://localhost:8080/swagger-ui/index.html
```

## Using Docker
To run the application using Docker, follow these steps:

### 1. Pull the Docker Image
Pull the Docker image from Docker Hub using the following command:

**Heroku**
```bash
docker pull matheusmagal/neoforum:v2
```

**Openshift**
```bash
docker pull matheusmagal/neoforum-dev:v1
```

### 2. Run the Docker Container
Run the Docker container using the following command:

**Heroku**
```bash
docker run -p 8080:8080 matheusmagal/neoforum
```

**Openshift**
```bash
docker run -p 8080:8080 matheusmagal/neoforum-dev
```

## Accessing the Application in Production
The application is hosted and accessible online. You can view the live version of the application at: [Neoforum API](https://neoforum-d6443e11a63d.herokuapp.com/swagger-ui/index.html).

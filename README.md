# Spring Coin App

A secure full-stack Spring Boot application for managing coins and mints, with both web UI and REST API support.

## Features

- Spring Boot 3 with Thymeleaf UI
- Spring Security with user roles
- H2 and MySQL support via profiles
- REST API for Coins and Mints
- Swagger UI at `/swagger-ui.html`
- Docker & Render deployment
- GitHub Actions CI with JaCoCo test coverage

## REST API Base URLs

- `GET /api/coins`
- `GET /api/mints`
- `POST /api/coins`
- `POST /api/mints`
- `PUT /api/coins/{id}`
- `DELETE /api/coins/{id}`

## Deployment

Use the included `render.yaml` to deploy to [Render](https://render.com) in one click.

---

## Swagger & Postman

- Swagger docs: `http://localhost:8080/swagger-ui.html`
- Import the Postman collection from `postman/SpringCoinApp.postman_collection.json`

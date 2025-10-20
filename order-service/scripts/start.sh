#!/usr/bin/env bash
# Arranque para dev (H2)
export SPRING_PROFILES_ACTIVE=
# Para Postgres (prod):
# export SPRING_PROFILES_ACTIVE=prod
# export DB_URL="jdbc:postgresql://localhost:5432/orders"
# export DB_USER=postgres
# export DB_PASSWORD=postgres


mvn -q -DskipTests spring-boot:run
#!/usr/bin/env sh
set -eu

SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)

docker run --rm \
  --name fields-service-builder \
  -v "$SCRIPT_DIR":/workspace \
  -v fields-service-m2-cache:/root/.m2 \
  -w /workspace \
  maven:3.9.9-eclipse-temurin-17 \
  sh -lc "chmod +x mvnw && ./mvnw -DskipTests package"

docker compose up -d --build

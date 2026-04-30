# agro-fields-service

Java-микросервис управления полями, контурами, культурами и севооборотами.

## Стек
- Java 17
- Spring Boot
- PostgreSQL/PostGIS
- Liquibase
- Docker / Docker Compose

## Быстрый запуск
```bash
docker network create agronetwork 2>/dev/null || true
docker compose up -d --build
```

Сервис доступен на `http://localhost:8004`, Swagger - `http://localhost:8004/docs`.
База данных доступна на `localhost:5434`.

## Локальная сборка и тесты
```bash
./mvnw clean package -DskipTests
./mvnw test
```

## Переменные окружения
Конфигурация хранится в `.env` и используется сервисом и контейнером БД.

## Дополнительно
- SQL-миграции находятся в `src/main/resources/db/changelog/migrations`.
- Для CDC/Debezium в контейнере БД включён `wal_level=logical`.

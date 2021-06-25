# CO-2 Demo

# FOR FRONTEND

1. Install docker-compose
2. cd demo
3. sudo docker-compose up --build


# FOR DEVELOPER
## Prerequisites

1. Install `docker`
2. Install Java 16, e.g. via `openjdk-16`
3. Install maven. Please make sure you are running a maven version compatible with the Java 16 Release, e.g. maven version `3.8.1`

**Disclaimer**: `mvn` version `3.8.1` as well as `openjdk-16` are not in Ubuntu 20.04 repositories.

## Running the database

This application uses a My-SQL database to persist data.

To start the database, simply run 
```bash
docker run --rm --name co2mysqldemo -p 3306:3306 --env MYSQL_ROOT_PASSWORD=secret --env MYSQL_DATABASE=demo  mysql
```

## Running the application

You can import the project in an IDE of your choice and go on from there, or you can run the application on the command line using `maven` via
```bash
mvn spring-boot:run
```

## Interacting with the application

### Uploading data

You can upload data using any tool of your choice to perform REST calls, e.g.
```bash
curl --header "Content-Type: application/json" \
--request POST \
--data '[{"firstName":"Frodo","lastName":"Baggins"}]' \
http://localhost:8080/api/v1/user

curl --header "Content-Type: application/json" \
--request POST \
--data '[{"firstName":"Bilbo","lastName":"Baggins"}]' \
http://localhost:8080/api/v1/user
```

### Downloading data

You can download data using any tool of your choice to perform REST calls, e.g.
```bash
curl http://localhost:8080/api/v1/user/id/1
curl http://localhost:8080/api/v1/user/id/2
curl http://localhost:8080/api/v1/user/name/Baggins
```

Running these commands assume you have inserted data into the application using the commands from the previous chapter.

## Architecture of the application

This application does not use the best practice for software architecture, as we do not fully de-couple layers using inversion of control.

However, the following layers are present:
```
    View
      ^
      |
   Service
      |
      v
     Data 
```
The `view` should handle incoming requests, the `data` layer should write to the database.

The `service` layer should take care of the "business logic", i.e. **mapping between input logic and data logic**.

Typically speaking, this means mapping between DTOs (Data Transfer Objects) and Entities.

The typical use case here is that you would not always necessarily need all the information from a data object returned via a request.


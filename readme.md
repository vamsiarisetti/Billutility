# Sample Header

This app deals with CRUD Operations on products catalog and monthly billing cycles

## Run Locally

Clone the project

```
  mvn clean install
  mvn spring-boot:run
```

## Swagger

We are using swagger 3 as a maven dependency, So we do not need any dependency for swagger-ui
and EnableSwagger2 is also not needed in starter
class ( [SwaggerConfig](src/main/java/com/org/utility/SwaggerConfig.java) )

## Swagger URL in local

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Run application using Docker

run below command to create a docker image with required mysql and spring boot

```
docker-compose up
```

docker command to check process status

```
docker ps
```

docker command to check list of docker images created

```
docker images
```

## Swagger URL in Docker

[http://localhost:6868/swagger-ui/index.html](http://localhost:6868/swagger-ui/index.html)

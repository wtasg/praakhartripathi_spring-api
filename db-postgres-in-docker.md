* install docker as your OS and follow anything you like maybe Yt or AI bots or documentation
* `docker pull postgres:15` or `docker pull postgres:vesion or latest`
* `docker start postgres-db`
* `docker exec -it postgres-db psql -U postgres -d db_name`
* `docker ps`
* `docker stop image-id` - to stop the image
* 

```postgres
docker run -d \
  --name postgres-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=db_name \
  -p 5432:5432 \
  postgres:15
```

```
spring.application.name=db_name

spring.datasource.url=jdbc:postgresql://localhost:5432/db_name
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

```

* `docker pull postgres:15` or `docker pull postgres:vesion or latest`
* `docker start postgres-db`
* `docker exec -it postgres-db psql -U postgres -d db_name`

```postgres
docker run -d \
  --name postgres-db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=db_name \
  -p 5432:5432 \
  postgres:15
```

* download docker using doc or yt or bot according you os
* docker pull mysql:oraclelinux9

```sql

docker run -d \
--name mysql-pos \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=pos_db \
-e MYSQL_USER=pos_user \
-e MYSQL_PASSWORD=pos_pass \
-p 3306:3306 \
mysql:8.0

  ```
* docker ps -a to check
* docker start mysql-pos
* docker exec -it mysql-pos mysql -u root -p
* enter password

* for stop `docker stop mysql-pos`
* for remove`docker rm mysql-pos`
* 


```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/pos_db
spring.datasource.username=pos_user
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
```

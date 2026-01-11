* download docker using doc or yt or bot according you os
* docker pull mysql:8.0
* ```sql
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

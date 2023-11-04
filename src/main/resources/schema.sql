/*
- CREATE DATABASE WITH POSTGRES
docker run --name demo-postgres -p 5432:5432 -e POSTGRES_DB=demo -e POSTGRES_USER=demo -e POSTGRES_PASSWORD=password -d postgres
*/

CREATE TABLE CUSTOMER (
  ID varchar(100) NOT NULL,
  NAME varchar(100) NOT NULL,
  AGE integer NOT NULL,
  CONSTRAINT PK_USER_ID PRIMARY KEY (ID)
);
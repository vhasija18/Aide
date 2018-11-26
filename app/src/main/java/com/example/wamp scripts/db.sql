CREATE DATABASE IF NOT EXISTS aide;

create table users(id integer AUTO_INCREMENT primary key,first_name text(15) not null,last_name text(15) not null,email varchar(50) not null,phone bigint(10) not null,dob date,pin varchar(50) not null,gender integer,occupation integer,latitude double,longitude double,type integer);
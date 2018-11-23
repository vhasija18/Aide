CREATE DATABASE IF NOT EXISTS aide;

create table users(id integer AUTO_INCREMENT primary key,name text not null,email text not null,phone integer not null, pin integer,gender integer, occupation integer, latitude double, longitude double,type integer);
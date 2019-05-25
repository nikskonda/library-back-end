drop SCHEMA public cascade;

create schema public;

drop database if exists "library1";
CREATE database "library1"	
with owner = postgres
ENCODING = 'UTF8';

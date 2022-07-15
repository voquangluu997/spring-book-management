create table roles(
	id serial not null primary key,
	name varchar(255) not null
);

insert into roles (name) values ('ROLE_ADMIN'),('ROLE_USER');

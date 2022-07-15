create table users(
	id serial not null primary key,
	email varchar(255) not null,
	password varchar(255) not null,
	first_name varchar(255),
	last_name varchar(255),
	enabled boolean not null,
	avatar varchar(255),
	role_id int not null,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

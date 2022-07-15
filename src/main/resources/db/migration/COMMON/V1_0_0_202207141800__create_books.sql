
create table books(
	id serial not null primary key,
	title varchar(1000) not null,
	author varchar(1000) not null,
	description varchar(2000),
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	image varchar(1000),
	enabled boolean not null,
	user_id int not null,
	FOREIGN KEY (user_id) REFERENCES users(id)
);

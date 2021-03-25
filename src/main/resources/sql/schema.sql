create table todo (
	id serial primary key,
	title varchar(100) not null,
	content varchar(1000),
	due_date date not null,
	is_done boolean not null
)

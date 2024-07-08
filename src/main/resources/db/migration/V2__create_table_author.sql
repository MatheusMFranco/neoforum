create table author(
	id bigint not null auto_increment,
	name varchar(50) not null,
	email varchar(50) not null,
	avatar varchar(25) not null,
	register_date datetime not null,
	primary key(id)
);

insert into author(id, name, email, avatar, register_date) values (1, 'Fox', 'fox@fox.com', 'fox', CURRENT_DATE);
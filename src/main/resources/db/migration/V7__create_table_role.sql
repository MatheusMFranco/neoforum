create table role(
	id bigint not null auto_increment,
	name varchar(50) not null,
	primary key(id)
);

insert into role(id, name) values (1, 'READ_AND_WRITE');
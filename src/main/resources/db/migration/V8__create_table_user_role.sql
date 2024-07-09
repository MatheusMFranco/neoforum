create table user_role(
	id bigint not null auto_increment,
	author_id bigint not null,
	role_id bigint not null,
	primary key(id),
	foreign key(author_id) references author(id),
	foreign key(role_id) references role(id)
);

insert into user_role(id, author_id, role_id) values (1, 1, 1);
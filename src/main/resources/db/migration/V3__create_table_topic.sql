create table topic(
	id bigint not null auto_increment,
	title varchar(50) not null,
	message varchar(255) not null,
	register_date datetime not null,
	board_id bigint not null,
	author_id bigint not null,
	status varchar(32) not null,
	primary key(id),
	foreign key(board_id) references board(id),
	foreign key(author_id) references author(id)
);

insert into topic(id, title, message, register_date, status, board_id, author_id) values (1, 'Duvida sobre kotlin', 'Minha funcao let nao funciona', '2021-12-24 12:00:00', 'EMPTY', 1, 1);

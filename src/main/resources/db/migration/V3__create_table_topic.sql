create table topic(
	id bigint not null auto_increment,
	title varchar(50) not null,
	message varchar(255) not null,
	register_date datetime not null,
	board_id bigint not null,
	author_id bigint not null,
	status varchar(10) not null,
	primary key(id),
	foreign key(board_id) references board(id),
	foreign key(author_id) references author(id)
);

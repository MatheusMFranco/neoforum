create table answer(
	id bigint not null auto_increment,
	message varchar(255) not null,
	register_date datetime not null,
	topic_id bigint not null,
	author_id bigint not null,
	primary key(id),
	foreign key(topic_id) references topic(id),
	foreign key(author_id) references author(id)
);
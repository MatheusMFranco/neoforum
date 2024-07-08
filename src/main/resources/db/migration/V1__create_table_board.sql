create table board(
	id bigint not null auto_increment,
	name varchar(50) not null,
	description varchar(255) not null,
	primary key(id)
);

insert into board(id, name, description) values (1, 'Official', 'staff members will post about technical glitches, things going unexpectedly wrong etc');
insert into board(id, name, description) values (2, 'Plot', 'here users can discuss anything and everything about the plot, including theories, prizes, plot steps and more');
insert into board(id, name, description) values (3, 'Events', 'originally only visible and functioning during events');
insert into board(id, name, description) values (4, 'Art', 'is a place to discuss the various art-based aspects ');
insert into board(id, name, description) values (5, 'Quests', 'is where you go to ask kind out there to find you a shop for the item');
insert into board(id, name, description) values (6, 'Stocks', 'is where serious stock discussion takes place');
insert into board(id, name, description) values (7, 'Help', 'you will most likely need to call on this board at least once');
insert into board(id, name, description) values (8, 'Ideas', 'is a place for people to contribute ideas to get them sounded out before passing them on');
insert into board(id, name, description) values (9, 'Trading', 'is a place to sell particularly unbuyable items');
insert into board(id, name, description) values (10, 'Role Play', 'for the uninitiated, role playing involves taking on a character and taking turns to act out a story on the boards');
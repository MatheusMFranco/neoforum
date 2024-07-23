insert into author (name, email, password, avatar, register_date) values ('tnt', 'tnt@email.com', '$2a$12$Dpr9yBjZksrrC34hnQEG1uDyF5HKckz3Cob4j5md1Jl3jXPF1ejzi', 'tnt', CURRENT_DATE);
insert into author (name, email, password, avatar, register_date) values ('cash', 'cash@email.com', '$2a$12$Dpr9yBjZksrrC34hnQEG1uDyF5HKckz3Cob4j5md1Jl3jXPF1ejzi', 'premium', CURRENT_DATE);
insert into role (name) values ('MONITOR');
insert into role (name) values ('PREMIUM');
insert into user_role (author_id, role_id) values (3, 3);
insert into user_role (author_id, role_id) values (4, 4);
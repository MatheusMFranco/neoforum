insert into author (name, email, password, avatar, register_date) values ('admin', 'admin@email.com', '$2a$12$Dpr9yBjZksrrC34hnQEG1uDyF5HKckz3Cob4j5md1Jl3jXPF1ejzi', 'tnt', CURRENT_DATE);
insert into role (name) values ('ADMIN');
insert into user_role (author_id, role_id) values (2, 2);
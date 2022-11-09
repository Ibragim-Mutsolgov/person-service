insert into medical_card (id, client_status, med_status, registry_dt, comment) VALUES (1, 'c', 'm', '2022-10-23', 'comment');
insert into medical_card (id, client_status, med_status, registry_dt, comment) VALUES (2, 'c', 'm', '2022-10-24', 'comment');

insert into contact (id, phone_number, email, profile_link) VALUES ('1', '9999999999', 'example@mail.ru', 'profile.link');
insert into contact (id, phone_number, email, profile_link) VALUES ('2', '9999999999', 'example@mail.ru', 'profile.link');

insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) VALUES ('1', '1', '1', 'h', '2022-10-10', '2022-10-23');
insert into illness (id, medical_card_id, type_id, heaviness, appearance_dttm, recovery_dt) values ('2', '2', '2', 'h', '2022-10-10', '2022-10-23');

insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values ('1', 'Ivanov', 'Ivan', '1997-10-27', '18', 'M', '1', '1', null);
insert into person_data (id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id) values ('2', 'Sergeyev', 'Sergey', '1997-10-27', '25', 'M', '2', '2', '1');

insert into address (id, contact_id, country_id, city, index, street, building, flat) values ('1', '1', '1', 'Moscow', '1', 'Naberejnaya', '7', '5');
insert into address (id, contact_id, country_id, city, index, street, building, flat) values ('2', '2', '2', 'Moscow', '1', 'Aviamotornaya', '8', '7');

insert into signals(id, person_data_id, description, type) VALUES (1, 1, 'dawd', 'alert');
insert into signals(id, person_data_id, description, type) VALUES (2, 2, 'dawd', 'alert');
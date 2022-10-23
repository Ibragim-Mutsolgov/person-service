create table if not exists medical_card(
    id bigserial primary key not null,
    client_status char(1) not null,
    med_status char(1) not null,
    registry_dt date not null,
    comment text
);

create table if not exists contact(
    id bigserial primary key not null,
    phone_number varchar(32) not null,
    email varchar(128) not null,
    profile_link text
);

create table if not exists illness(
    id bigserial primary key not null,
    medical_card_id bigint references medical_card(id) not null,
    type_id bigint,
    heaviness char(1),
    appearance_dttm timestamp not null,
    recovery_dt date
);

create table if not exists person_data(
    id bigserial primary key not null,
    last_name varchar(255) not null,
    first_name varchar(255) not null,
    birth_dt date not null,
    age smallint,
    sex char(1) not null,
    contact_id bigint references contact(id) not null,
    medical_card_id bigint unique references medical_card(id) not null,
    parent_id bigint check (parent_id <> id) references person_data(id)
);

create table if not exists address(
    id bigserial primary key not null,
    contact_id bigint references contact(id) not null,
    country_id bigint not null,
    city varchar(255) not null,
    index int,
    street varchar(255) not null,
    building varchar(32) not null,
    flat varchar(32)
);
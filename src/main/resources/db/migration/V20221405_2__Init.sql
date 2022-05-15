create table teams_admin_credential(
    id serial primary key,
    directory_id varchar(255) not null,
    grant_type varchar(255) not null,
    client_id varchar(255) not null,
    client_secret varchar(255) not null,
    admin_id bigint not null,
    constraint fk_admin_id foreign key (admin_id) references commission(id)
);

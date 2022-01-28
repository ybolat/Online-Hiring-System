create table role(
    id serial primary key,
    role_name varchar(255) not null unique
);

create table academic_degree(
    id serial primary key,
    title varchar(255) not null unique
);

create table users(
    id serial primary key,
    email varchar(255) not null unique,
    password varchar(500) not null,
    role_id bigint not null,
    name varchar(255) not null,
    lastname varchar(255) not null,
    patronymic varchar(255),
    phone varchar(20) not null,
    position varchar(255),
    is_active boolean default false,
    is_locked boolean default false,
    created_date timestamp not null,
    constraint fk_role_id foreign key (role_id) references role (id)
);

create table user_professional_info(
    id serial primary key,
    user_id bigint not null,
    academic_degree_id bigint not null,
    academic_title varchar(255),
    scopus_id varchar(500),
    research_id varchar(500) not null,
    google_scholar varchar(255),
    orcid varchar(255),
    experience varchar(255),
    scientific_interests varchar(255),
    education varchar(255),
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_academic_degree_id foreign key (academic_degree_id) references academic_degree (id)
);

create table commission(
    id serial primary key,
    role_id bigint not null,
    email varchar(255) not null unique,
    constraint fk_role_id foreign key (role_id) references role (id)
);

create table project_type(
    id serial primary key,
    title varchar(255) not null unique
);

create table status(
    id serial primary key,
    title varchar(255) not null unique
);

create table subject(
    id serial primary key,
    title_en varchar(255),
    title_ru varchar(255),
    title_kz varchar(255),
    description_en text,
    description_ru text,
    description_kz text,
    code varchar(255) not null,
    volume_credits int not null,
    academic_degree_id bigint not null,
    constraint fk_academic_degree_id foreign key (academic_degree_id) references academic_degree (id)
);

create table request(
    id serial primary key,
    user_id bigint not null,
    status_id bigint not null,
    created_date timestamp not null,
    background text,
    additional text,
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_status_id foreign key (status_id) references status (id)
);

create table article_type(
    id serial primary key,
    title varchar(255) not null unique
);

create table article(
    id serial primary key,
    apa text,
    doi text,
    article_type_id bigint not null,
    link text,
    user_id bigint not null,
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_article_type_id foreign key (article_type_id) references article_type (id)
);

create table assessment(
    id serial primary key,
    commission_id bigint not null,
    request_id bigint not null,
    rate Float,
    constraint fk_commission_id foreign key (commission_id) references commission (id),
    constraint fk_request_id foreign key (request_id) references request (id)
);

create table certificate(
    id serial primary key,
    user_id bigint not null,
    certificate text not null,
    constraint fk_user_id foreign key (user_id) references users (id)
);

create table development(
    id serial primary key,
    user_id bigint not null,
    name varchar(255) not null,
    description text,
    constraint fk_user_id foreign key (user_id) references users (id)
);

create table intelligence_legal_document(
    id serial primary key,
    user_id bigint not null,
    document text not null,
    constraint fk_user_id foreign key (user_id) references users (id)
);

create table meeting(
    id serial primary key,
    name varchar(255),
    link varchar(500),
    date_time timestamp not null,
    request_id bigint not null,
    constraint  fk_request_id foreign key (request_id) references request (id)
);

create table commission_meeting
(
    meeting_id    bigint not null,
    commission_id bigint not null,
    primary key (meeting_id, commission_id)
);

create table project(
    id serial primary key,
    user_id bigint not null,
    started_date timestamp not null,
    finished_date timestamp,
    sum Float,
    fund varchar(255),
    project_type_id bigint not null,
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_project_type_id foreign key (project_type_id) references project_type (id)
);

create table syllabus(
    id serial primary key,
    user_id bigint not null,
    subject_id bigint not null,
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_subject_id foreign key (subject_id) references subject (id)
);

create table syllabus_by_week(
    id serial primary key,
    syllabus_id bigint not null,
    week_number int not null,
    title varchar(255) not null ,
    description text,
    constraint fk_syllabus_id foreign key (syllabus_id) references syllabus (id)
)

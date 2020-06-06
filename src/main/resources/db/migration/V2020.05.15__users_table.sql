create table users
(
    user_id   serial  not null
        constraint users_pk
            primary key,
    name      varchar not null,
    surname   varchar not null,
    login     varchar not null,
    password  varchar not null,
    job       varchar not null,
    photolink bytea
);

create unique index users_login_uindex
    on users (login);

create unique index users_user_id_uindex
    on users (user_id);


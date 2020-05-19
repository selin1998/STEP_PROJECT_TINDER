-- auto-generated definition
create table messages
(
    message_id   serial  not null
        constraint messages_pk
            primary key,
    user_id_from integer not null,
    user_id_to   integer not null,
    message      varchar,
    time         varchar not null
);



create unique index messages_message_id_uindex
    on messages (message_id);

-- auto-generated definition
create table likes
(
    like_id      serial  not null
        constraint likes_pk
            primary key,
    user_id_from integer not null,
    user_id_to   integer not null
);



create unique index likes_like_id_uindex
    on likes (like_id);

-- auto-generated definition
create table activity
(
    user_id     integer not null
        constraint activity_pk
            primary key,
    logout_time varchar
);


create unique index activity_user_id_uindex
    on activity (user_id);



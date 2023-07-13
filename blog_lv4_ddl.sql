create table if not exists post.user
(
    user_id       bigint auto_increment
        primary key,
    user_email    varchar(36)            not null,
    user_password varchar(64)            not null,
    user_role     enum ('ADMIN', 'USER') not null,
    user_username varchar(12)            not null,
    constraint UK_jnu1quvkutdk73q9fa4d7abe3
        unique (user_username)
);

create table if not exists post.post
(
    post_id            bigint auto_increment
        primary key,
    created_date       datetime(6)   null,
    last_modified_date datetime(6)   null,
    post_contents      text          not null,
    post_likes_count   int default 0 not null,
    post_title         varchar(32)   not null,
    post_username      varchar(12)   not null,
    user_id            bigint        not null,
    constraint FK72mt33dhhs48hf9gcqrq4fxte
        foreign key (user_id) references post.user (user_id)
);

create table if not exists post.comment
(
    comment_id          bigint auto_increment
        primary key,
    created_date        datetime(6)   null,
    last_modified_date  datetime(6)   null,
    comment_contents    varchar(255)  not null,
    comment_username    varchar(12)   not null,
    post_id             bigint        not null,
    user_id             bigint        not null,
    comment_likes_count int default 0 not null,
    constraint FK8kcum44fvpupyw6f5baccx25c
        foreign key (user_id) references post.user (user_id),
    constraint FKs1slvnkuemjsq2kj4h3vhx7i1
        foreign key (post_id) references post.post (post_id)
);

create table if not exists post.comment_likes
(
    comment_likes_id bigint auto_increment
        primary key,
    comment_id       bigint null,
    user_id          bigint null,
    constraint FKd0epu3dcjc57pwe7lt5jgfqsi
        foreign key (comment_id) references post.comment (comment_id),
    constraint FKgtjsp4k7rsoon6lnxjjx7cnqp
        foreign key (user_id) references post.user (user_id)
);

create table if not exists post.post_likes
(
    post_likes_id bigint auto_increment
        primary key,
    post_id       bigint null,
    user_id       bigint null,
    constraint FKc85he3c94qv5vmyutmf0plp69
        foreign key (user_id) references post.user (user_id),
    constraint FKmxmoc9p5ndijnsqtvsjcuoxm3
        foreign key (post_id) references post.post (post_id)
);


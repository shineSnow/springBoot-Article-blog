create table if not exists t_article
(
    id          bigint auto_increment
        primary key,
    title       varchar(255)                       not null,
    author      varchar(50)                        null,
    content     mediumtext                         null,
    tag         varchar(255)                       null,
    summary     text                               null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '文章表';

create table if not exists t_permission
(
    id          bigint auto_increment
        primary key,
    menu_title  varchar(50)                        not null,
    parent_id   bigint                             not null,
    menu_type   int                                not null,
    menu_sort   int                                not null,
    menu_path   varchar(100)                       null,
    menu_icon   varchar(100)                       null,
    component   varchar(100)                       null,
    is_hidden   int      default 0                 null,
    permission  varchar(50)                        null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '权限表';

create table if not exists t_role
(
    id          bigint auto_increment
        primary key,
    role_name   varchar(50)                        not null,
    role_code   varchar(50)                        not null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    role_mark   varchar(255)                       null
)
    comment '角色表';

create table if not exists t_role_permission
(
    id          bigint auto_increment
        primary key,
    rid         bigint                             not null,
    perm_id     bigint                             not null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '角色权限表';

create table if not exists t_tag
(
    id          bigint auto_increment
        primary key,
    tag         varchar(50)                        not null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create table if not exists t_user
(
    id          bigint auto_increment
        primary key,
    username    varchar(100) default ''                null,
    password    varchar(255)                           null,
    email       varchar(255)                           null,
    sex         int                                    null,
    create_time timestamp    default CURRENT_TIMESTAMP null,
    update_time timestamp    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '用户表';

create table if not exists t_user_role
(
    id          bigint auto_increment
        primary key,
    uid         bigint                             not null,
    rid         bigint                             not null,
    create_time datetime default CURRENT_TIMESTAMP null,
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '用户角色表';



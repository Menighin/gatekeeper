create table users
(
    id         bigserial constraint users_pk primary key,
    username   varchar(128) not null,
    password   text not null,
    locked     boolean not null,
    enabled    boolean not null,
    created_at timestamp not null,
    created_by varchar(256),
    updated_at timestamp,
    updated_by varchar(256)
);

create unique index users_username_uindex
    on users (username);

create table roles
(
    id         bigserial constraint roles_pk primary key,
    name       varchar(128) not null,
    created_at timestamp not null,
    created_by varchar(256),
    updated_at timestamp,
    updated_by varchar(256)
);

create unique index roles_name_uindex
    on roles (name);

create table permissions
(
    id         bigserial constraint permissions_pk primary key,
    name       varchar(128) not null,
    created_at timestamp not null,
    created_by varchar(256),
    updated_at timestamp,
    updated_by varchar(256)
);

create unique index permissions_name_uindex
    on permissions (name);

create table users_roles
(
    user_id    bigserial not null constraint users_roles_users_id_fk references users on delete cascade,
    role_id    bigserial not null constraint users_roles_roles_id_fk references roles on delete cascade,
    created_at timestamp not null,
    created_by varchar(256),
    updated_at timestamp,
    updated_by varchar(256)
);

create unique index users_roles_user_id_role_id_uindex
    on users_roles (user_id, role_id);

create table users_permissions
(
    user_id       bigserial not null constraint users_permissions_users_id_fk references users on delete cascade,
    permission_id bigserial not null constraint users_permissions_permissions_id_fk references permissions on delete cascade,
    created_at timestamp not null,
    created_by varchar(256),
    updated_at timestamp,
    updated_by varchar(256)
);

create unique index users_permissions_user_id_permission_id_uindex
    on users_permissions (user_id, permission_id);







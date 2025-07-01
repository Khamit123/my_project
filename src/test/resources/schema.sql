create table if not exists risk_type
(
    title       varchar(100) not null,
    id          bigserial
        constraint id primary key,
    description text
);

create table if not exists country
(
    id         bigserial
        constraint country_pk
            primary key,
    title      varchar(100)  not null,
    day_premium float        not null
);
create table if not exists age_coef
(
    id       bigserial
        constraint age_pk primary key,
    age_from integer not null,
    age_to   integer not null,
    coef     float   not null

);

create table if not exists insurance_limit
(
    id       bigserial
        constraint limit_pk primary key,
    limit_min float not null,
    limit_max   float not null,
    coef     float   not null
);
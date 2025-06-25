create table if not exists public.risk_type
(
    title       varchar(50) not null,
    id          bigserial
        constraint id primary key,
    description text
);

create table if not exists public.country
(
    id         bigserial
        constraint country_pk
            primary key,
    title      varchar(100) not null,
    DayPremium float        not null
);

create table if not exists public.age_coef
(
    id       bigserial
        constraint age_pk primary key,
    age_from integer not null,
    age_to   integer not null,
    coef     float   not null

);

create table if not exists public.insurance_limit
(
    id       bigserial
        constraint limit_pk primary key,
    limit_min float not null,
    limit_max   float not null,
    coef     float   not null
);
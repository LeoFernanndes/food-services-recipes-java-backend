create table recipes
(
    id bigint not null primary key,
    name varchar(255),
    preparation_time_minutes integer,
    recipe varchar(255)
);
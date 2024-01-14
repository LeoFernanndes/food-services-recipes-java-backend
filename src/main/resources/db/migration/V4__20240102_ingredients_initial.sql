create table ingredients
(
    id varchar not null primary key,
    name varchar not null,
    quantity float not null,
    measurement_unit varchar not null,
    recipe varchar,
    CONSTRAINT fk_recipe_id FOREIGN KEY(recipe) REFERENCES recipes(id)
);
CREATE TABLE table_b
(
    id serial primary key,
    some_column varchar
);

CREATE TABLE table_c
(
    id serial primary key,
    additional_column varchar
);

INSERT INTO table_b(id, some_column)
VALUES (1, 'first'),
       (2, 'second');

INSERT INTO table_c(id, additional_column)
VALUES (1, 'additional');

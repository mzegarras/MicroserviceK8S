CREATE SEQUENCE country_id_seq;

CREATE TABLE country (
    id int NOT NULL PRIMARY KEY DEFAULT nextval('country_id_seq'),
    description character varying(255)
);



CREATE SEQUENCE country_id_seq;

CREATE SEQUENCE version_id_seq;


CREATE TABLE country (
    id int NOT NULL PRIMARY KEY DEFAULT nextval('country_id_seq'),
    description character varying(255)
);



CREATE TABLE version (
    id int NOT NULL PRIMARY KEY DEFAULT nextval('version_id_seq'),
    version character varying(100),
    tipo smallint,
    mandatory smallint,
    activo smallint
);


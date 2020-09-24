CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS bank_account;
DROP TABLE IF EXISTS active_rents;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS user_info;

DROP TYPE IF EXISTS ROLE_TYPE;
DROP TYPE IF EXISTS STATUS_TYPE;

CREATE TYPE ROLE_TYPE AS ENUM ('OWNER', 'CLIENT');
CREATE CAST (character varying AS ROLE_TYPE) WITH INOUT AS ASSIGNMENT;

CREATE TABLE IF NOT EXISTS user_info (
	id UUID DEFAULT uuid_generate_v4(),
	name TEXT NOT NULL,
	surname TEXT NOT NULL,
    login TEXT NOT NULL,
    email TEXT NOT NULL,
    password_hash TEXT NOT NULL,
    role ROLE_TYPE NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),

	PRIMARY KEY(id)
);

CREATE TYPE STATUS_TYPE AS ENUM ('AVAILABLE', 'RENTED');
CREATE CAST (character varying AS STATUS_TYPE) WITH INOUT AS ASSIGNMENT;


CREATE TABLE IF NOT EXISTS car (
	id UUID DEFAULT uuid_generate_v4(),
	model TEXT NOT NULL,
	type TEXT NOT NULL,
	price_per_hour INT NOT NULL CHECK (price_per_hour >= 0),
	status STATUS_TYPE NOT NULL,
	owner_id UUID NOT NULL,

	PRIMARY KEY(id),
	CONSTRAINT fk_owner_constraint FOREIGN KEY (owner_id) REFERENCES user_info(id) ON DELETE CASCADE 
);

CREATE TABLE IF NOT EXISTS active_rent (
	id UUID DEFAULT uuid_generate_v4(),
	client_id UUID NOT NULL,
	car_id UUID NOT NULL,
	duration INT NOT NULL CHECK (duration > 0),

	PRIMARY KEY (id),
	CONSTRAINT fk_client_constraint FOREIGN KEY (client_id) REFERENCES user_info(id) ON DELETE SET NULL,
	CONSTRAINT fk_car_constraint FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS bank_account (
	id UUID DEFAULT uuid_generate_v4(),
	user_id UUID NOT NULL,
	iban TEXT NOT NULL,
	balance INT NOT NULL,

	PRIMARY KEY (id),
	CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_info(id) ON DELETE CASCADE
);

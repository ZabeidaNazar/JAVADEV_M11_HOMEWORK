DROP TABLE IF EXISTS ticket;

DROP TABLE IF EXISTS client;

DROP TABLE IF EXISTS planet;


-- Client (клієнт) - клієнт компанії. Має наступні властивості:
-- id - ідентифікатор, первинний сурогатний ключ, автоінкрементне число.
-- name - ім'я, від 3 до 200 символів включно

CREATE TABLE client (
	id SERIAL PRIMARY KEY,
	name varchar(200) NOT NULL CONSTRAINT client_name_length CHECK (length(name) >= 3)
);


-- Planet (планета). Початковий або кінцевий пункт відправлення. Має наступні властивості:
-- id - ідентифікатор планети. Рядок, що складається виключно з латинських букв у верхньому регістрі та цифр. Наприклад, MARS, VEN
-- name - назва планети, рядок від 1 до 500 символів включно

CREATE TABLE planet (
	id varchar PRIMARY KEY CONSTRAINT planet_id_type CHECK (id ~ '^[A-Z0-9]{2,5}$'),
	name varchar(500) NOT NULL CONSTRAINT worker_name_length CHECK (length(name) >= 1)
);
-- CONSTRAINT planet_id_length CHECK (length(id) >= 2) 


-- Ticket (квиток). Має наступні властивості:
-- id - ідентифікатор квитка, первинний сурогатний ключ, автоінкрементне число.
-- created_at - TIMESTAMP в UTC, коли був створений цей квиток
-- client_id - ідентифікатор клієнта, якому належить цей квиток.
-- from_planet_id - ідентифікатор планети, звідки відправляється пасажир
-- to_planet_id - ідентифікатор планети, куди летить пасажир

CREATE TABLE ticket (
	id SERIAL PRIMARY KEY,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	client_id int CONSTRAINT client_id_from_ticket_with_id_from_client REFERENCES client(id) ON DELETE CASCADE,
	from_planet_id varchar(5) CONSTRAINT from_planet_id_from_ticket_with_id_from_planet REFERENCES planet(id) ON DELETE CASCADE,
	to_planet_id varchar(5) CONSTRAINT to_planet_id_from_ticket_with_id_from_planet REFERENCES planet(id) ON DELETE CASCADE
);

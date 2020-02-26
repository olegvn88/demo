CREATE TABLE person (id uniqueidentifier NOT NULL PRIMARY KEY, name VARCHAR(100) NOT NULL);

SELECT * FROM person

INSERT INTO person (id, name) VALUES (NEWID(),'John Travolta')

ALTER TABLE person ADD email VARCHAR(100)

UPDATE person SET person.email = 'email', person.country = 'ua'

SELECT id, name, country, email FROM person
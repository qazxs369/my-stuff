/*
Veo los nombres de todas las db
LOS STATEMENTS TERMINAN CON EL PUNTO Y COMA
*/
SHOW DATABASES;


/*
Creo base de datos
*/
CREATE DATABASE coffe_store;

/*
***IMPORTANTE***
Cada vez que se trabaja con una base usar el USE
*/
USE coffe_store;

/*
Creo tablas
*/
CREATE TABLE products (
	
    /*
    Creo el campo id como clave primaria que se autoincrementa cada vez que se crea una: 1, 2, 3...
    */
	id INT AUTO_INCREMENT PRIMARY KEY,
    
    /*
    VARCHAR es un string pero de tamaño variable, es decir que puede tener hasta longtud 30,
    si fuera CHAR(20) estaria forzado a tener tamaño 20 siempre
    */
    name VARCHAR(30),
    
    /*
    3 es la cantidad de digitos totales que puede tener y 2 los que vienen despues, 
    a diferencia de FLOAT, DECIMAL no redondea
    */
    price DECIMAL(3,2)
);

CREATE TABLE customers (
	
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    
    /*
    se usa para tipos personalizados
    */
    gender ENUM('M','F'),
    
    phone_number VARCHAR(11)
);
 
CREATE TABLE orders (

	id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    customer_id INT,
    order_time DATETIME,
    
    /*
    Creo clave foranea, product_id referencia a id en la tabla products, que es la clave primaria de products
    */
    FOREIGN KEY (product_id) REFERENCES products(id),
    
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

/*
Veo los nombres de todas las tablas en la db que este usando
*/
SHOW TABLES;

/*
Me devuelve la data de la tabla, * te devuelve todo o podes poner la/s columna/s que queres que te devuelva
***IMPORTANTE***
HACER ESTO PARA VER RESULTADOS
*/
SELECT * FROM products;

SELECT last_name, gender FROM customers;

/*
El WHERE sirve para imponer una condicion en la query
*/
SELECT * FROM products 
WHERE coffee_origin = 'Colombia';

SELECT * FROM products 
WHERE price <= 3.00;

/*
Puedo meter mas de una condicion usando AND, puedo meter todos los AND que quiera
*/
SELECT * FROM products 
WHERE price = 3.50
AND coffee_origin = 'Colombia';

/*
Analogo con OR
*/
SELECT * FROM products 
WHERE price = 3.00
OR coffee_origin = 'Colombia';

/*
¿Que pasa si tengo un null en algun campo?
*/
SELECT * FROM customers
WHERE phone_number IS NULL;

SELECT * FROM customers
WHERE phone_number IS NOT NULL;

/*
***IMPORTANTE***
Cada vez que quiera cambiar algo en una tabla se empieza con ALTER TABLE <nombre de la tabla>
Se usa para ADD/DROP COLUMN, ADD/DROP PRIMARY KEY, ADD CONSTRAINT(...FOREIGN KEY/DROP FOREIGN KEY 
(nombre del constraint de foreign key))/DROP INDEX, CHANGE, RENAME TO
*/

/*
Agrego columna
*/
ALTER TABLE products
ADD COLUMN coffee_origin VARCHAR(30);

/*
Quito columna
*/
ALTER TABLE products
DROP COLUMN coffee_origin;



CREATE DATABASE test;

USE test;

CREATE TABLE addresses (

	id INT,
    house_number INT,
    city VARCHAR(20),
    postcode VARCHAR(7)
);

CREATE TABLE people (
	
    id INT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    address_id INT
);

CREATE TABLE pets (

	id INT,
    name_column VARCHAR(20),
    species VARCHAR(20),
    owner_id INT
);

SHOW TABLES;

/*
Muestra para cada campo de la tabla su respectivo tipo, si puede tener null o no, que tipo de clave es, etc
*/
DESCRIBE addresses;

DESCRIBE people;

/*
Puedo agregar una clave primaria despues de crear la tabla
*/
ALTER TABLE addresses
ADD PRIMARY KEY (id);

/*
Quito clave primaria
*/
ALTER TABLE addresses
DROP PRIMARY KEY;

ALTER TABLE people
ADD PRIMARY KEY (id);

/*
Analogo con las claves foraneas pero si hace falta agregar un constraint, le ponemos un nombre
Los constraints son limitadores de que datos van en una columna, por ejemplo cuando le ponemos 
el tipo de datos de la columna es una especie de constraint. Tenemos UNIQUE, CHECK, NO NULL, DEFAULT, PRIMARY KEY, FOREIGN KEY
*/
ALTER TABLE people
ADD CONSTRAINT FK_PeopleAddress
FOREIGN KEY (address_id) REFERENCES addresses(id);

/*
Para quitar la clave foranea hace falta dropear su constraint
*/
ALTER TABLE people
DROP FOREIGN KEY FK_PeopleAddress;

SELECT * FROM pets;

/*
Agrego la limitacion UNIQUE para que no pueda tener valores repetidos en species
*/
ALTER TABLE pets
ADD CONSTRAINT u_species UNIQUE (species);

/*
Para quitarla le dropeamos el index
*/
ALTER TABLE pets
DROP INDEX u_species;

/*
Cambio el nombre de un campo/columna
*/
ALTER TABLE pets CHANGE `species` `animal_type` VARCHAR(20);

ALTER TABLE pets CHANGE `animal_type` `species` VARCHAR(20);

/*
Cambio el tipo de datos de un campo
*/
ALTER TABLE addresses MODIFY city VARCHAR(20);

DESCRIBE addresses;



DESCRIBE pets;

ALTER TABLE pets
ADD PRIMARY KEY (id);

DESCRIBE people;

ALTER TABLE pets
ADD CONSTRAINT FK_OwnerID
FOREIGN KEY (owner_id) REFERENCES people(id);

ALTER TABLE people
ADD COLUMN email VARCHAR(30);

ALTER TABLE people
ADD CONSTRAINT u_email UNIQUE (email);

ALTER TABLE pets CHANGE `name_column` `first_name` VARCHAR(20);

ALTER TABLE addresses MODIFY postcode CHAR(7);



SELECT * FROM products;

/*
Inserto una fila en la tabla, no hace falta especificar la columna id porque se autoincrementa por estar configurado asi.
OJO: si en algun momento borro todo en la tabla y quiero resetear el contador debia haber borrado con TRUNCATE TABLE
*/
INSERT INTO products (name, price, coffee_origin)
VALUES ('Cafesito', 2.50, 'Brasil');

/*
Puedo insertar mas de una fila en el mismo statement
*/
INSERT INTO products (name, price, coffee_origin)
VALUES ('Otro Cafesito', 3.00, 'Brasil'),('Y otro Cafesito', 3.50, 'Costa Rica');

INSERT INTO products (name, price, coffee_origin)
VALUES ('Latte', 3.50, 'Indonesia'),('Americano', 3.00, 'Brazil'),('Flat white', 3.50, 'Indonesia'),('Filter', 3.00, 'India');

/*
***IMPORTANTE***
Cada vez que quiera cambiar algun dato de una tabla se empieza con UPDATE
*/

UPDATE products
SET coffee_origin = 'Sri Lanka'
/*
OJO que por defecto solo se puede usar el campo que sea clave primaria, salvo que haga SET SQL_SAFE_UPDATES=0;
*/
WHERE id = 7;

SET SQL_SAFE_UPDATES=0;

UPDATE products
SET price = 3.25, coffee_origin = 'Ethiopia'
WHERE name = 'Americano';

UPDATE products
SET coffee_origin = 'Colombia'
WHERE coffee_origin = 'Brasil';

CREATE DATABASE example;

USE example;

CREATE TABLE people(

	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    age INT,
    gender ENUM('M','F')
);

SELECT * FROM people;

INSERT INTO people (name, age, gender)
VALUES ('Emma', 21, 'F'),('John', 30, 'M'),('Thomas', 27, 'M'),('Chris', 44, 'M'),('Sally', 23, 'F'),('Frank', 55, 'M');

/*
Para borrar no hace falta un ALTER TABLE
*/
DELETE FROM people;

/*
si no le pongo WHERE borra todo en la tabla
*/
DELETE FROM people
WHERE gender = 'F';

/*
Tambien puedo borrar con TRUNCATE que ademas resetea cualquier AUTO_INCREMENT
*/
TRUNCATE TABLE people;


/*----------------------------------------------------------------------------------*/


/*
Para cambiar el nombre de una tabla
*/
ALTER TABLE costumers RENAME TO customers;

TRUNCATE TABLE customers;

SELECT * FROM customers;

INSERT INTO customers (first_name, last_name, gender, phone_number) 
VALUES ('Chris','Martin','M','01123147789'),('Emma','Law','F','01123439899'),('Mark','Watkins','M','01174592013'),('Daniel','Williams','M',NULL),('Sarah','Taylor','F','01176348290'),('Katie','Armstrong','F','01145787353'),('Michael','Bluth','M','01980289282'),('Kat','Nash','F','01176987789'),('Buster','Bluth','M','01173456782'),('Charlie',NULL,'F','01139287883'),('Lindsay','Bluth','F','01176923804'),('Harry','Johnson','M',NULL),('John','Smith','M','01174987221'),('John','Taylor','M',NULL),('Emma','Smith','F','01176984116'),('Gob','Bluth','M','01176985498'),('George','Bluth','M','01176984303'),('Lucille','Bluth','F','01198773214'),('George','Evans','M','01174502933'),('Emily','Simmonds','F','01899284352'),('John','Smith','M','01144473330'),('Jennifer',NULL,'F',NULL),('Toby','West','M','01176009822'),('Paul','Edmonds','M','01966947113');

SELECT * FROM orders;

DESCRIBE orders;

ALTER TABLE orders CHANGE `costumer_id` `customer_id` INT;

/*
Cambie el nombre de un campo que era una foreign key que era costumer_id, pero como la hice sin nombrar 
el constraint tengo que averiguar su constraint por defecto
*/
SELECT CONSTRAINT_NAME
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'orders';
/*
igual cuando haga un INSERT en orders me salta que el problema esta en el constraint "orders_ibfk_2"
*/

INSERT INTO orders (product_id,customer_id,order_time) 
VALUES (1,1,'2017-01-01 08-02-11'),(1,2,'2017-01-01 08-05-16'),(5,12,'2017-01-01 08-44-34'),(3,4,'2017-01-01 09-20-02'),(1,9,'2017-01-01 11-51-56'),(6,22,'2017-01-01 13-07-10'),(1,1,'2017-01-02 08-03-41'),(3,10,'2017-01-02 09-15-22'),(2,2,'2017-01-02 10-10-10'),(3,13,'2017-01-02 12-07-23'),(1,1,'2017-01-03 08-13-50'),(7,16,'2017-01-03 08-47-09'),(6,21,'2017-01-03 09-12-11'),(5,22,'2017-01-03 11-05-33'),(4,3,'2017-01-03 11-08-55'),(3,11,'2017-01-03 12-02-14'),(2,23,'2017-01-03 13-41-22'),(1,1,'2017-01-04 08-08-56'),(3,10,'2017-01-04 11-23-43'),(4,12,'2017-01-05 08-30-09'),(7,1,'2017-01-06 09-02-47'),(3,18,'2017-01-06 13-23-34'),(2,16,'2017-01-07 09-12-39'),(2,14,'2017-01-07 11-24-15'),(4,5,'2017-01-08 08-54-11'),(1,1,'2017-01-09 08-03-11'),(6,20,'2017-01-10 10-34-12'),(3,3,'2017-01-10 11-02-11'),(4,24,'2017-01-11 08-39-11'),(4,8,'2017-01-12 13-20-13'),(1,1,'2017-01-14 08-27-10'),(4,15,'2017-01-15 08-30-56'),(1,7,'2017-01-16 10-02-11'),(2,10,'2017-01-17 09-50-05'),(1,1,'2017-01-18 08-22-55'),(3,9,'2017-01-19 09-00-19'),(7,11,'2017-01-19 11-33-00'),(6,12,'2017-01-20 08-02-21'),(3,14,'2017-01-21 09-45-50'),(5,2,'2017-01-22 10-10-34'),(6,24,'2017-01-23 08-32-19'),(6,22,'2017-01-23 08-45-12'),(6,17,'2017-01-23 12-45-30'),(2,11,'2017-01-24 08-01-27'),(1,1,'2017-01-25 08-05-13'),(6,11,'2017-01-26 10-49-10'),(7,3,'2017-01-27 09-23-57'),(7,1,'2017-01-27 10-08-16'),(3,18,'2017-01-27 10-13-09'),(4,19,'2017-01-27 11-02-40'),(3,10,'2017-01-28 08-03-21'),(1,2,'2017-01-28 08-33-28'),(1,12,'2017-01-28 11-55-33'),(1,13,'2017-01-29 09-10-17'),(6,6,'2017-01-30 10-07-13'),(1,1,'2017-02-01 08-10-14'),(2,14,'2017-02-02 10-02-11'),(7,10,'2017-02-02 09-43-17'),(7,20,'2017-02-03 08-33-49'),(4,21,'2017-02-04 09-31-01'),(5,22,'2017-02-05 09-07-10'),(3,23,'2017-02-06 08-15-10'),(2,24,'2017-02-07 08-27-26'),(1,1,'2017-02-07 08-45-10'),(6,11,'2017-02-08 10-37-10'),(3,13,'2017-02-09 08-58-18'),(3,14,'2017-02-10 09-12-40'),(5,4,'2017-02-10 11-05-34'),(1,2,'2017-02-11 08-00-38'),(3,8,'2017-02-12 08-08-08'),(7,20,'2017-02-12 09-22-10'),(1,1,'2017-02-13 08-37-45'),(5,2,'2017-02-13 12-34-56'),(4,3,'2017-02-14 08-22-43'),(5,4,'2017-02-14 09-12-56'),(3,5,'2017-02-15 08-09-10'),(6,7,'2017-02-15 09-05-12'),(1,8,'2017-02-15 09-27-50'),(2,9,'2017-02-16 08-51-12'),(3,10,'2017-02-16 13-07-46'),(4,11,'2017-02-17 08-03-55'),(4,12,'2017-02-17 09-12-11'),(5,10,'2017-02-17 11-41-17'),(6,18,'2017-02-17 13-05-56'),(7,19,'2017-02-18 08-33-27'),(1,17,'2017-02-19 08-12-31'),(1,1,'2017-02-20 09-50-17'),(3,5,'2017-02-20 09-51-29'),(4,6,'2017-02-20 10-43-39'),(3,1,'2017-02-21 08-32-17'),(1,1,'2017-02-21 10-30-11'),(3,2,'2017-02-21 11-08-45'),(4,3,'2017-02-22 11-46-32'),(2,15,'2017-02-22 13-35-16'),(6,13,'2017-02-23 08-34-48'),(4,24,'2017-02-24 08-32-03'),(2,13,'2017-02-25 08-03-12'),(7,17,'2017-02-25 09-34-23'),(7,23,'2017-02-25 11-32-54'),(5,12,'2017-02-26 11-47-34'),(6,4,'2017-02-27 12-12-34'),(1,1,'2017-02-28 08-59-22');

ALTER TABLE orders
DROP FOREIGN KEY orders_ibfk_2;


SELECT * FROM customers;
SELECT first_name, phone_number FROM customers
WHERE gender = 'F'
AND last_name = 'Bluth';

SELECT * FROM products;
SELECT name FROM products
WHERE price > 3.00
OR coffee_origin = 'Sri Lanka';

SELECT * FROM customers
WHERE gender = 'M'
AND phone_number IS NULL;

/*
Podemos especificar mas de un valor a la vez
*/
SELECT * FROM customers
WHERE last_name IN ('Taylor', 'Bluth', 'Armstrong');

SELECT * FROM customers
WHERE first_name NOT IN ('Katie', 'John', 'George');


SELECT * FROM orders;
SELECT * FROM customers;

/*
Puedo filtrar datos por rango tambien
*/
SELECT product_id, customer_id, order_time FROM orders
WHERE order_time BETWEEN '2017-01-01' AND '2017-01-07';

SELECT product_id, customer_id, order_time FROM orders
WHERE customer_id BETWEEN 5 AND 10;

SELECT * FROM customers
WHERE last_name BETWEEN 'A' AND 'L';


/*
Pattern Matching
Con % trae lo que empiece con W y siga con cualquier cantidad de caracteres
Con _ lo que siga con solo 1 caracter
*/
SELECT * FROM customers
WHERE last_name LIKE 'W%';

/*
Funciona de los dos lados
*/
SELECT * FROM customers
WHERE last_name LIKE '%o%';

SELECT * FROM customers
WHERE first_name LIKE '_o_';

/*
Guarda que hay que usar '' incluso con INT
*/
SELECT * FROM products
WHERE price LIKE '3%';


/*
Puedo ordenar los datos de manera ascendente
*/
SELECT * FROM products
ORDER BY price ASC;

/*
O descendiente
*/
SELECT * FROM products
ORDER BY price DESC;

/*
OJO que los null aparecen primero
*/
SELECT * FROM customers
ORDER BY last_name ASC;

SELECT * FROM orders
WHERE customer_id = 1
ORDER BY order_time DESC;


/*----------------------------------------------------------------------------------*/


SELECT name, price FROM products
WHERE coffee_origin IN ('Colombia', 'Indonesia')
ORDER BY name ASC;

SELECT * FROM orders
WHERE order_time LIKE '2017-02%'
AND customer_id IN (2, 4, 6, 8);

SELECT first_name, phone_number FROM customers
WHERE last_name LIKE '%ar%';


/*
Si quiero valores distintos
*/
SELECT DISTINCT coffee_origin FROM products;

/*
Y asi consigo todas las posibles combinaciones entre mas de 1 campo
*/
SELECT DISTINCT customer_id, product_id FROM orders
WHERE order_time LIKE '2017-02%';


/*
Puedo especificar la cantidad de filas con LIMIT, OJO que el recorte lo hace despues de un orden por ejemplo
*/
SELECT * FROM customers
ORDER BY last_name
LIMIT 10;

/*
Tambien puedo hacer que se saltee con OFFSET
*/
SELECT * FROM customers
LIMIT 5 OFFSET 5;


/*
Puedo ponerle un alias a los campos (en el resultado, no cambia nada en la db)
*/
SELECT name AS coffee, price, coffee_origin AS country FROM products;



SELECT DISTINCT last_name FROM customers
ORDER BY last_name ASC;

SELECT * FROM orders
WHERE customer_id = 1
AND order_time LIKE '2017-02%'
ORDER BY order_time ASC
LIMIT 3;

SELECT name, price AS retail_price, coffee_origin FROM products;


SELECT * FROM products;
SELECT * FROM orders;

/*
Con esto hago un JOIN, en este caso es la interseccion, un INNER JOIN. Tiene que haber una relacion entre las columnas,
en este caso la id de productos
OJO que si tengo varias tablas con datos, hay que especificar de que tabla es cada dato
*/
SELECT products.name, orders.order_time FROM orders
INNER JOIN products ON orders.product_id = products.id;

/*
Atajo
*/
SELECT p.name, p.price, o.order_time FROM orders o
JOIN products p ON o.product_id = p.id
WHERE o.product_id = 5
ORDER BY o.order_time;


UPDATE orders
SET customer_id = NULL
WHERE id = 1;

SELECT * FROM customers;
SELECT * FROM orders;

/*
TABLA 1: orders
TABLA 2: customers
LEFT JOIN me muestra toda la data de orders y toda la data de customers que matchee con orders
En este caso en la tabla orders puse NULL en la primer fila con id = 1 en la columna customer_id en donde conectaba con customers
por lo que rompi el link entre customer_id = 1 e id = 1 y entonces el LEFT JOIN no pudo traer data de customers para el id = 1
pero todo lo de orders si
*/
SELECT o.id, c.phone_number, c.last_name, o.order_time FROM orders o
LEFT JOIN customers c ON o.customer_id = c.id
ORDER BY o.order_time
LIMIT 10;

/*
TABLA 1: customers
TABLA 2: orders
Ahora que customers es la primer tabla LEFT JOIN trata de encontrar data de customers pero no puede porque cortamos el link para id = 1
entonces no puede traer nada de id = 1
*/
SELECT o.id, c.phone_number, c.last_name, o.order_time FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
ORDER BY o.order_time
LIMIT 10;


/*
Analogo con RIGHT JOIN
*/
SELECT o.id, c.phone_number, c.last_name, o.order_time FROM customers c
RIGHT JOIN orders o ON c.id = o.customer_id
ORDER BY o.order_time
LIMIT 10;

SELECT o.id, c.phone_number, c.last_name, o.order_time FROM orders o
RIGHT JOIN customers c ON o.customer_id = c.id
ORDER BY o.order_time
LIMIT 10;


/*
Se puede joinear mas de 2 en un statement
*/
SELECT * FROM products;
SELECT * FROM customers;
SELECT * FROM orders;

/*
Como no hay relacion entre products y customers tengo que unir a traves de orders
*/
SELECT p.name, p.price, c.first_name, c.last_name, o.order_time FROM products p
JOIN orders o ON p.id = o.product_id
JOIN customers c ON c.id = o.customer_id
/*Tambien se puede agregar WHERE o cosas asi*/
WHERE c.last_name = 'Martin'
ORDER BY o.order_time;


/*----------------------------------------------------------------------------------*/


SELECT * FROM products;
SELECT * FROM customers;
SELECT * FROM orders;

SELECT o.id, c.phone_number FROM orders o
JOIN customers c ON o.customer_id = c.id
/*Aca no hace falta joinear products tambien porque puedo sacar la id del producto desde orders!!!!!11!!*/
WHERE o.product_id = 4;

SELECT p.name, o.order_time FROM products p
JOIN orders o ON p.id = o.product_id
WHERE p.name = 'Filter'
AND o.order_time BETWEEN '2017-01-15' AND '2017-02-14';

SELECT p.name, p.price, o.order_time FROM products p
JOIN orders o ON p.id = o.product_id
JOIN customers c ON c.id = o.customer_id
WHERE c.gender = 'F'
AND o.order_time LIKE '2017-01%';

/*
IMPORTANTE
Todo esto de andar separando en tablas esta buenisimo para hacer uso de "NORMALIZACION" que significa no tener campos repetidos
ni con info no relacionada. Por ejemplo si yo tuviera una tabla de estudiantes con los campos student, year, class, teacher
y siendo student y year la clave primaria podria suceder que varios alumnos tengan la misma clase y profe, es decir
que sin contar la clave primaria tengo filas repetidas, hay data redundante. En ese caso conviene tener una tabla de clases 
que tenga la columna student_id asi cuando yo quiera hacer un select de la clase de los estudiantes le hago JOIN con 
students.id = class.student_id como venimos haciendo, y asi con cualquier campo que se repita. La Normalizacion te 
ahorra problemas de insercion, actualizacion y eliminacion!
*/


/*----------------------------------------------------------------------------------*/


CREATE TABLE players (
	
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE games (

	id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT,
    FOREIGN KEY (player_id) REFERENCES players(id)
);

ALTER TABLE games CHANGE `player_id` `player1_id` INT;

ALTER TABLE games
ADD COLUMN player2_id INT;

ALTER TABLE games
ADD CONSTRAINT FK_player2ID
FOREIGN KEY (player2_id) REFERENCES players(id);

DESCRIBE players;
DESCRIBE games;

SELECT * FROM players;
SELECT * FROM games;

INSERT INTO players (name)
VALUES ('niño_de_relleno_uno'),('niño_de_relleno_dos'),('niño_de_relleno_tres');

INSERT INTO games (player1_id, player2_id)
VALUES (1,2), (2,3);

INSERT INTO games (player1_id, player2_id)
VALUES (3,NULL);

INSERT INTO games (player1_id, player2_id)
VALUES (1,NULL);

INSERT INTO games (player1_id, player2_id)
VALUES (NULL, 1);

INSERT INTO games (player1_id, player2_id)
VALUES (3,1);

INSERT INTO games (player1_id, player2_id)
VALUES (1,3);

/*
Quiero todos los games de player 1
*/
SELECT p.id AS player_id, g.id AS game_id FROM players p
JOIN games g ON g.player1_id = p.id
OR g.player2_id = p.id
WHERE p.id = 1;

/*
¿Que pasa si quiero todos los players del game 1?
*/
SELECT g.id AS game_id, p.id AS player_id FROM games g
JOIN players p ON g.player1_id = p.id
OR g.player2_id = p.id
WHERE g.id = 7;

/*
¿POR QUE HACE FALTA UNA TERCER TABLA? porque capa que el dia de mañana hay mas datos en la relacion. Por ejemplo,
si yo quisiera el puntaje de un juego ¿donde puede ir? si se lo pongo a player un puntaje necesitara el juego particular
y si a game le pongo un puntaje necesita ese player particular
*/


/*----------------------------------------------------------------------------------*/


SHOW DATABASES;

CREATE DATABASE cinema_booking_system;

USE cinema_booking_system;

CREATE TABLE films (

	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL UNIQUE,
    length_min INT NOT NULL
);

SHOW TABLES;

SELECT * FROM films;

DESCRIBE films;


CREATE TABLE customers (

	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(45),
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE
);

SHOW TABLES;

SELECT * FROM customers;

DESCRIBE customers;


CREATE TABLE rooms (

	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    /*significa number of seats jaja*/
    no_seats INT NOT NULL
);

SHOW TABLES;

DESCRIBE rooms;


CREATE TABLE screenings (

	id INT PRIMARY KEY AUTO_INCREMENT,
    film_id INT NOT NULL,
    room_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    FOREIGN KEY (film_id) REFERENCES films(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);

SHOW TABLES;

DESCRIBE screenings;


CREATE TABLE seats (

	id INT PRIMARY KEY AUTO_INCREMENT,
    seat_row CHAR(1) NOT NULL,
    seat_num INT NOT NULL,
    room_id INT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);

SHOW TABLES;

DESCRIBE seats;


CREATE TABLE bookings (

	id INT PRIMARY KEY AUTO_INCREMENT,
    screening_id INT NOT NULL,
    customer_id INT NOT NULL,
    FOREIGN KEY (screening_id) REFERENCES screenings(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

SHOW TABLES;

DESCRIBE bookings;


CREATE TABLE reserved_seat (

	id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    seat_id INT NOT NULL,
    FOREIGN KEY (booking_id) REFERENCES bookings(id),
    FOREIGN KEY (seat_id) REFERENCES seats(id)
);

SHOW TABLES;

DESCRIBE reserved_seat;


/*----------------------------------------------------------------------------------*/


SELECT * FROM films;
SELECT * FROM customers;
SELECT * FROM rooms;
SELECT * FROM screenings;
SELECT * FROM seats;
SELECT * FROM bookings;
SELECT * FROM reserved_seat;

INSERT INTO films (name, length_min)
VALUES ('Blade Runner 2049',153),
('Dunkirk',106),
('Geostorm',121),
('Thor: Ragnarok',107),
('Jigsaw',116),
('The Death of Stalin',98),
('The Lego Ninjago Movie',101),
('Murder on the Orient Express',135),
('Paddington 2',88),
('Breathe',117),
('Blade Runner',127),
('Victoria and Abdul',112);

INSERT INTO customers (first_name, last_name, email)
VALUES ('David','May','dmay@gmail.com'),('Theresa','Davis','tdavis@gmail.com'),('Jerry','Martin','jermar@gmail.com'),('John','Smith','smithy@gmail.com'),
('Martin','Watkins','mwatto@gmail.com'),('Emma','Watson','itswingardiumleviosa@gmail.com'),('Javier','Pastore','jp@gmail.com'),('Charlotte','Hare','chare@gmail.com'),
('Mark','Smith','markysmith@gmail.com'),('Emily','Thorne','ethorne@gmail.com'),('David','Paul','davpaul@gmail.com'),('Cherry','Wang','cherryw@gmail.com'),
('Bort','Simpson','mynameisbort@gmail.com'),('Emma','Connor','econ@gmail.com'),('Jonathan','Edwards','jedwards@gmail.com'),('Simon','Davis','sdavis@gmail.com'),
('Sophie','Marshall','sophmarsh@gmail.com'),('Sally','Lam','slam@gmail.com'),('Kenneth','Harrison','kson@gmail.com'),(NULL,'Smith','smith@gmail.com'),
('Harry','Weasley','whatsmyname@gmail.com'),('Ron','Potter','thatsmyname@gmail.com'),('Ray','Wilkinson','wilky@gmail.com'),('Samuel','Windsor','royalty@gmail.com'),
('Jennifer','David','jda@gmail.com'),('Larry','Davis','larryd@gmail.com'),('Bolt','Tin','btin@gmail.com'),('Sarah','Thompson','sthompson1977@gmail.com'),
('Alan','Bennet','thosewerethedays@gmail.com'),('Brian','Apple','bapple101@gmail.com'),('Rob','Bryson','rbry@gmail.com'),('Shelly','Zu','szu@gmail.com'),
('Steve','Shoogan','sshoogan@gmail.com'),('Matthew','Gray','graym@gmail.com'),('Sally','Kramer','kallysramer@gmail.com'),('Peter','Chef','chefp@gmail.com'),
(NULL,'Cooke','nullcooke@gmail.com'),('Marty','Seinfeld','marfeld@gmail.com'),('Carl','Green','creen@gmail.com'),('Emma','Greene','egreene@gmail.com'),
('Michael','Bannon','mbannon@gmail.com'),('Paul','Showman','theshowman1@gmail.com'),('Harry','Goldthistle','hgold@gmail.com'),('Gill','Newton','gnew@gmail.com'),
('Preston','Peters','p2p@gmail.com'),('Simon','Smithson','sismithson@gmail.com'),('Benjamin','Rabbit','brabbit@gmail.com'),('Jerry','David','curb@gmail.com'),
('Olivia','Tulip','otulip@gmail.com'),('Richard','Horne','richhorne@gmail.com'),('Betty','Corbe','bcorbe@gmail.com'),('Anne','Callaway','acway@gmail.com'),
('Yifan','Way','yway@gmail.com'),('Charlie','Cheene','ccheene@gmail.com'),('Ula','Boon','uboon@gmail.com'),('Buster','David','bdavid@gmail.com'),
('Dustin','Beer','beerd@gmail.com'),('John','Sleet','knoweverything@gmail.com'),('August','Summer','asummer@gmail.com'),('Linda','Martins','lmart@gmail.com'),
('Chris','Kay','chkay@gmail.com'),('Christopher','Roberts','croberts@gmail.com'),('Robert','Winston','rwin@gmail.com'),('Daniel','Orbe','dorbe@gmail.com'),
('Joshua','Wells','wellj@gmail.com'),('Fred','Flinton','ff100@gmail.com'),('Paula','Winner','pwinner@gmail.com'),(NULL,'Canter','canter99@gmail.com'),
('Michael','Bassett','mbass@gmail.com'),('Michelle','Martins','mm109@gmail.com'),('Lorna','Brown','lbrown@gmail.com'),('Kerry','Vale','wish1985@gmail.com'),
('Sean','Borne','sborne@gmail.com'),('Craig','Evans','cevans@gmail.com'),('Alex','Sanders','asand@gmail.com'),('George','McNabb','gnabb@gmail.com'),
('Richard','Trout','redtrout@gmail.com'),('Stewart','Dee','sdee@gmail.com'),('Sue','Werkins','swerkins@gmail.com'),('Mel','Wozniack','mwoz@gmail.com'),
('Steve','Dole','sdole@gmail.com'),('Zara','Yelland','zy27@gmail.com'),('Irene','Mellon','mellonirene@gmail.com'),('Karen','Wall','kwall@gmail.com'),
('Lucy','Oak','loak@gmail.com'),('Orson','Young','oyoung@gmail.com'),('Oliver','Daniels','odaniels@gmail.com'),('Queen','Bee','qbee@gmail.com'),
('Vera','Jack','verajack@gmail.com'),('Thomas','Soft','softt@gmail.com'),(NULL,'Blair','nblair@gmail.com'),('Tim','Jones','tjones@gmail.com'),
('Nicola','Jenkins','njenk@gmail.com'),('Olivia','Jones','ojo@gmail.com'),('James','Depp','jdepp@gmail.com'),('Meryl','Steep','msteep@gmail.com'),
('Jake','Portman','jportman@gmail.com'),('Jenny','Appleton','japple@gmail.com'),('Simon','Hoover','shoover@gmail.com'),('Belinda','Marshall','belindam@gmail.com'),
('Stephanie','Watson','swatson@gmail.com'),('Art','Simons','simons@gmail.com'),('Stan','Smith','smithstan@gmail.com'),('Christopher','Roberts','robertsc@gmail.com'),
('Nathan','Gold','engold@gmail.com'),('Alain','Bennet','alainb@gmail.com'),('Lewis','Russelton','lrus@gmail.com'),('Josh','Reynolds','jrey@gmail.com'),
('William','Williams','williamw@gmail.com'),(NULL,'Pointer','apointer@gmail.com'),('Bort','Skinner','mynameisalsobort@gmail.com'),(NULL,'Smith','lonsmith@gmail.com'),
('Susan','Williams','swill@gmail.com'),('Yu','Xi','yuxi@gmail.com'),('Annabelle','Winks','anwinks@gmail.com'),('Andrew','Landhurst','andylandy@gmail.com'),
('Winston','Smith','room101@gmail.com'),('Gemma','Arton','garton@gmail.com'),('Renton','McDonald','rent@gmail.com'),('Maggie','Chu','chuchu@gmail.com');

INSERT INTO rooms (name, no_seats) 
VALUES ('Chaplin',72),
('Kubrick',36),
('Coppola',36);

INSERT INTO screenings (film_id, room_id, start_time)
VALUES (2,1,'2017-10-01 13:00:00'),(1,2,'2017-10-01 13:30:00'),(2,3,'2017-10-01 14:00:00'),
(3,1,'2017-10-01 16:00:00'),(6,2,'2017-10-01 16:30:00'),(3,3,'2017-10-01 17:00:00'),
(4,1,'2017-10-01 19:00:00'),(6,2,'2017-10-01 19:30:00'),(4,3,'2017-10-01 20:00:00'),
(1,1,'2017-10-02 16:00:00'),(4,2,'2017-10-02 16:30:00'),(5,3,'2017-10-02 17:00:00'),
(2,1,'2017-10-02 19:00:00'),(1,2,'2017-10-02 19:30:00'),(1,3,'2017-10-02 20:00:00'),
(5,1,'2017-10-03 16:00:00'),(3,2,'2017-10-03 16:30:00'),(2,3,'2017-10-03 17:00:00'),
(1,1,'2017-10-03 19:00:00'),(2,2,'2017-10-03 19:30:00'),(3,3,'2017-10-03 20:00:00'),
(3,1,'2017-10-04 16:00:00'),(1,2,'2017-10-04 16:30:00'),(4,3,'2017-10-04 17:00:00'),
(2,1,'2017-10-04 19:00:00'),(1,2,'2017-10-04 19:30:00'),(5,3,'2017-10-04 20:00:00'),
(3,1,'2017-10-05 16:00:00'),(4,2,'2017-10-05 16:30:00'),(6,3,'2017-10-05 17:00:00'),
(2,1,'2017-10-05 19:00:00'),(6,2,'2017-10-05 19:30:00'),(3,3,'2017-10-05 20:00:00'),
(1,1,'2017-10-06 16:00:00'),(5,2,'2017-10-06 16:30:00'),(2,3,'2017-10-06 17:00:00'),
(4,1,'2017-10-06 19:00:00'),(2,2,'2017-10-06 19:30:00'),(4,3,'2017-10-06 20:00:00'),
(2,1,'2017-10-07 13:00:00'),(1,2,'2017-10-07 13:30:00'),(2,3,'2017-10-07 14:00:00'),
(3,1,'2017-10-07 16:00:00'),(1,2,'2017-10-07 16:30:00'),(1,3,'2017-10-07 17:00:00'),
(3,1,'2017-10-07 19:00:00'),(2,2,'2017-10-07 19:30:00'),(1,3,'2017-10-07 20:00:00'),
(7,1,'2017-10-08 13:00:00'),(1,2,'2017-10-08 13:30:00'),(1,3,'2017-10-08 14:00:00'),
(7,1,'2017-10-08 16:00:00'),(3,2,'2017-10-08 16:30:00'),(2,3,'2017-10-08 17:00:00'),
(2,1,'2017-10-08 19:00:00'),(5,2,'2017-10-08 19:30:00'),(7,3,'2017-10-08 20:00:00'),
(1,1,'2017-10-09 16:00:00'),(7,2,'2017-10-09 16:30:00'),(8,3,'2017-10-09 17:00:00'),
(3,1,'2017-10-09 19:00:00'),(8,2,'2017-10-09 19:30:00'),(9,3,'2017-10-09 20:00:00'),
(1,1,'2017-10-10 16:00:00'),(6,2,'2017-10-10 16:30:00'),(6,3,'2017-10-10 17:00:00'),
(3,1,'2017-10-10 19:00:00'),(9,2,'2017-10-10 19:30:00'),(6,3,'2017-10-10 20:00:00'),
(8,1,'2017-10-11 16:00:00'),(4,2,'2017-10-11 16:30:00'),(5,3,'2017-10-11 17:00:00'),
(9,1,'2017-10-11 19:00:00'),(3,2,'2017-10-11 19:30:00'),(4,3,'2017-10-11 20:00:00'),
(4,1,'2017-10-12 16:00:00'),(7,2,'2017-10-12 16:30:00'),(6,3,'2017-10-12 17:00:00'),
(3,1,'2017-10-12 19:00:00'),(7,2,'2017-10-12 19:30:00'),(7,3,'2017-10-12 20:00:00'),
(5,1,'2017-10-13 16:00:00'),(2,2,'2017-10-13 16:30:00'),(8,3,'2017-10-13 17:00:00'),
(9,1,'2017-10-13 19:00:00'),(2,2,'2017-10-13 19:30:00'),(9,3,'2017-10-13 20:00:00'),
(1,1,'2017-10-14 13:00:00'),(4,2,'2017-10-14 13:30:00'),(6,3,'2017-10-14 14:00:00'),
(2,1,'2017-10-14 16:00:00'),(1,2,'2017-10-14 16:30:00'),(5,3,'2017-10-14 17:00:00'),
(3,1,'2017-10-14 19:00:00'),(7,2,'2017-10-14 19:30:00'),(7,3,'2017-10-14 20:00:00'),
(2,1,'2017-10-15 13:00:00'),(10,2,'2017-10-15 13:30:00'),(11,3,'2017-10-15 14:00:00'),
(2,1,'2017-10-15 16:00:00'),(10,2,'2017-10-15 16:30:00'),(11,3,'2017-10-15 17:00:00'),
(3,1,'2017-10-15 19:00:00'),(10,2,'2017-10-15 19:30:00'),(11,3,'2017-10-15 20:00:00'),
(1,1,'2017-10-16 16:00:00'),(4,2,'2017-10-16 16:30:00'),(4,3,'2017-10-16 17:00:00'),
(4,1,'2017-10-16 19:00:00'),(4,2,'2017-10-16 19:30:00'),(2,3,'2017-10-16 20:00:00'),
(7,1,'2017-10-17 16:00:00'),(5,2,'2017-10-17 16:30:00'),(1,3,'2017-10-17 17:00:00'),
(8,1,'2017-10-17 19:00:00'),(7,2,'2017-10-17 19:30:00'),(7,3,'2017-10-17 20:00:00'),
(9,1,'2017-10-18 16:00:00'),(8,2,'2017-10-18 16:30:00'),(6,3,'2017-10-18 17:00:00'),
(6,1,'2017-10-18 19:00:00'),(3,2,'2017-10-18 19:30:00'),(5,3,'2017-10-18 20:00:00'),
(5,1,'2017-10-19 16:00:00'),(2,2,'2017-10-19 16:30:00'),(9,3,'2017-10-19 17:00:00'),
(3,1,'2017-10-19 19:00:00'),(4,2,'2017-10-19 19:30:00'),(9,3,'2017-10-19 20:00:00'),
(1,1,'2017-10-20 16:00:00'),(1,2,'2017-10-20 16:30:00'),(8,3,'2017-10-20 17:00:00'),
(2,1,'2017-10-20 19:00:00'),(6,2,'2017-10-20 19:30:00'),(4,3,'2017-10-20 20:00:00'),
(1,1,'2017-10-21 13:00:00'),(9,2,'2017-10-21 13:30:00'),(7,3,'2017-10-21 14:00:00'),
(2,1,'2017-10-21 16:00:00'),(8,2,'2017-10-21 16:30:00'),(2,3,'2017-10-21 17:00:00'),
(3,1,'2017-10-21 19:00:00'),(7,2,'2017-10-21 19:30:00'),(1,3,'2017-10-21 20:00:00'),
(3,1,'2017-10-22 13:00:00'),(11,2,'2017-10-22 13:30:00'),(10,3,'2017-10-22 14:00:00'),
(2,1,'2017-10-22 16:00:00'),(11,2,'2017-10-22 16:30:00'),(10,3,'2017-10-22 17:00:00'),
(4,1,'2017-10-22 19:00:00'),(11,2,'2017-10-22 19:30:00'),(10,3,'2017-10-22 20:00:00'),
(6,1,'2017-10-23 16:00:00'),(6,2,'2017-10-23 16:30:00'),(9,3,'2017-10-23 17:00:00'),
(12,1,'2017-10-23 19:00:00'),(6,2,'2017-10-23 19:30:00'),(9,3,'2017-10-23 20:00:00'),
(5,1,'2017-10-24 16:00:00'),(9,2,'2017-10-24 16:30:00'),(9,3,'2017-10-24 17:00:00'),
(12,1,'2017-10-24 19:00:00'),(8,2,'2017-10-24 19:30:00'),(8,3,'2017-10-24 20:00:00'),
(8,1,'2017-10-25 16:00:00'),(7,2,'2017-10-25 16:30:00'),(7,3,'2017-10-25 17:00:00'),
(9,1,'2017-10-25 19:00:00'),(5,2,'2017-10-25 19:30:00'),(6,3,'2017-10-25 20:00:00'),
(12,1,'2017-10-26 16:00:00'),(7,2,'2017-10-26 16:30:00'),(5,3,'2017-10-26 17:00:00'),
(12,1,'2017-10-26 19:00:00'),(9,2,'2017-10-26 19:30:00'),(12,3,'2017-10-26 20:00:00'),
(5,1,'2017-10-27 16:00:00'),(12,2,'2017-10-27 16:30:00'),(12,3,'2017-10-27 17:00:00'),
(7,1,'2017-10-27 19:00:00'),(12,2,'2017-10-27 19:30:00'),(7,3,'2017-10-27 20:00:00'),
(6,1,'2017-10-28 13:00:00'),(12,2,'2017-10-28 13:30:00'),(5,3,'2017-10-28 14:00:00'),
(5,1,'2017-10-28 16:00:00'),(6,2,'2017-10-28 16:30:00'),(5,3,'2017-10-28 17:00:00'),
(5,1,'2017-10-28 19:00:00'),(7,2,'2017-10-28 19:30:00'),(8,3,'2017-10-28 20:00:00'),
(12,1,'2017-10-29 13:00:00'),(8,2,'2017-10-29 13:30:00'),(9,3,'2017-10-29 14:00:00'),
(12,1,'2017-10-29 16:00:00'),(9,2,'2017-10-29 16:30:00'),(6,3,'2017-10-29 17:00:00'),
(5,1,'2017-10-29 19:00:00'),(5,2,'2017-10-29 19:30:00'),(12,3,'2017-10-29 20:00:00'),
(5,1,'2017-10-30 16:00:00'),(7,2,'2017-10-30 16:30:00'),(12,3,'2017-10-30 17:00:00'),
(5,1,'2017-10-30 19:00:00'),(12,2,'2017-10-30 19:30:00'),(7,3,'2017-10-30 20:00:00'),
(5,1,'2017-10-31 13:00:00'),(12,2,'2017-10-31 13:30:00'),(8,3,'2017-10-31 14:00:00'),
(5,1,'2017-10-31 16:00:00'),(5,2,'2017-10-31 16:30:00'),(9,3,'2017-10-31 17:00:00');

INSERT INTO seats (seat_row, seat_num, room_id)
VALUES ('A',1,1),('A',2,1),('A',3,1),('A',4,1),('A',5,1),('A',6,1),('A',7,1),('A',8,1),
('B',1,1),('B',2,1),('B',3,1),('B',4,1),('B',5,1),('B',6,1),('B',7,1),('B',8,1),
('C',1,1),('C',2,1),('C',3,1),('C',4,1),('C',5,1),('C',6,1),('C',7,1),('C',8,1),
('D',1,1),('D',2,1),('D',3,1),('D',4,1),('D',5,1),('D',6,1),('D',7,1),('D',8,1),
('E',1,1),('E',2,1),('E',3,1),('E',4,1),('E',5,1),('E',6,1),('E',7,1),('E',8,1),
('F',1,1),('F',2,1),('F',3,1),('F',4,1),('F',5,1),('F',6,1),('F',7,1),('F',8,1),
('G',1,1),('G',2,1),('G',3,1),('G',4,1),('G',5,1),('G',6,1),('G',7,1),('G',8,1),
('H',1,1),('H',2,1),('H',3,1),('H',4,1),('H',5,1),('H',6,1),('H',7,1),('H',8,1),
('I',1,1),('I',2,1),('I',3,1),('I',4,1),('I',5,1),('I',6,1),('I',7,1),('I',8,1),
('A',1,2),('A',2,2),('A',3,2),('A',4,2),('A',5,2),('A',6,2),
('B',1,2),('B',2,2),('B',3,2),('B',4,2),('B',5,2),('B',6,2),
('C',1,2),('C',2,2),('C',3,2),('C',4,2),('C',5,2),('C',6,2),
('D',1,2),('D',2,2),('D',3,2),('D',4,2),('D',5,2),('D',6,2),
('E',1,2),('E',2,2),('E',3,2),('E',4,2),('E',5,2),('E',6,2),
('F',1,2),('F',2,2),('F',3,2),('F',4,2),('F',5,2),('F',6,2),
('A',1,3),('A',2,3),('A',3,3),('A',4,3),('A',5,3),('A',6,3),
('B',1,3),('B',2,3),('B',3,3),('B',4,3),('B',5,3),('B',6,3),
('C',1,3),('C',2,3),('C',3,3),('C',4,3),('C',5,3),('C',6,3),
('D',1,3),('D',2,3),('D',3,3),('D',4,3),('D',5,3),('D',6,3),
('E',1,3),('E',2,3),('E',3,3),('E',4,3),('E',5,3),('E',6,3),
('F',1,3),('F',2,3),('F',3,3),('F',4,3),('F',5,3),('F',6,3);

INSERT INTO bookings (screening_id, customer_id)
VALUES (2,2),(1,4),(2,6),(4,8),(6,10),(1,12),(5,14),(1,16),(2,18),(3,20),
(5,120),(4,12),(8,24),(10,33),(9,37),(4,67),(11,87),(4,109),(5,12),(6,21),
(8,11),(10,13),(14,7),(16,103),(18,81),(7,89),(17,52),(7,50),(8,49),(9,37),
(11,1),(16,37),(20,4),(22,23),(21,24),(10,24),(23,24),(10,55),(14,51),(12,69),
(14,3),(22,34),(26,33),(28,35),(27,65),(13,22),(29,22),(13,100),(20,101),(12,58),
(17,5),(28,29),(32,33),(34,45),(33,32),(19,32),(35,30),(16,17),(26,56),(18,55),
(17,7),(31,11),(38,26),(40,4),(30,51),(25,38),(41,37),(22,101),(32,99),(15,76),
(20,9),(37,10),(44,21),(46,55),(33,22),(31,33),(47,44),(25,55),(38,42),(24,1),
(23,11),(37,1),(50,77),(52,23),(39,1),(43,5),(53,89),(28,88),(44,37),(30,2),
(26,12),(40,1),(56,66),(58,27),(42,7),(46,45),(59,22),(31,25),(50,29),(27,9),
(29,14),(43,1),(62,55),(64,11),(45,8),(49,56),(65,11),(37,26),(56,120),(33,1),
(32,15),(49,1),(68,44),(70,11),(51,13),(55,58),(71,1),(49,33),(56,116),(36,103),
(35,17),(55,56),(74,33),(70,17),(54,11),(61,77),(71,45),(58,78),(62,36),(66,9),
(38,19),(61,88),(80,57),(76,120),(66,100),(67,89),(77,49),(58,89),(62,17),(69,61),
(41,100),(67,22),(86,45),(82,119),(72,27),(73,90),(80,56),(61,68),(68,20),(33,9),
(44,66),(76,119),(92,82),(88,100),(81,89),(79,5),(83,65),(64,42),(74,80),(42,26),
(47,45),(82,118),(98,67),(94,11),(81,55),(82,4),(83,23),(67,4),(86,80),(45,19),
(50,44),(88,117),(104,61),(100,97),(81,35),(85,3),(89,35),(76,41),(86,90),(48,17),
(53,33),(94,116),(110,4),(106,54),(84,33),(88,2),(95,34),(76,27),(86,29),(51,28),
(56,39),(97,115),(116,4),(112,32),(90,33),(88,1),(101,34),(82,90),(86,101),(51,104),
(59,111),(100,114),(122,7),(118,35),(93,57),(88,77),(107,69),(91,96),(92,4),(54,35),
(92,100),(106,113),(128,9),(124,60),(87,88),(91,39),(113,67),(91,43),(98,90),(60,36),
(98,99),(112,112),(134,10),(130,43),(69,84),(97,31),(119,1),(97,3),(104,7),(63,1),
(104,4),(118,111),(140,1),(136,22),(72,67),(97,111),(125,87),(100,24),(110,46),(66,4),
(110,17),(130,110),(146,12),(142,22),(75,48),(100,111),(131,47),(103,24),(122,27),(69,27),
(122,55),(136,91),(152,11),(148,13),(84,90),(103,101),(137,12),(109,11),(128,10),(75,9),
(128,58),(142,90),(158,56),(154,15),(93,91),(109,34),(143,9),(115,34),(131,33),(78,81),
(134,88),(148,48),(164,78),(160,16),(102,22),(115,33),(149,47),(121,46),(137,6),(90,6),
(140,67),(154,40),(170,77),(166,78),(108,21),(121,26),(155,53),(127,42),(143,31),(99,11),
(152,45),(160,70),(176,38),(172,66),(114,20),(127,106),(161,111),(211,109),(149,108),(102,113),
(158,44),(166,87),(182,99),(178,65),(120,19),(136,99),(167,49),(133,66),(158,67),(117,3),
(164,71),(172,33),(188,100),(184,50),(144,18),(142,23),(173,46),(139,23),(164,1),(129,3),
(170,78),(178,35),(194,36),(187,23),(159,24),(151,23),(179,55),(145,58),(170,60),(141,4),
(176,70),(184,7),(200,36),(190,78),(162,70),(157,80),(182,99),(151,95),(176,32),(156,7),
(182,88),(190,8),(200,34),(193,74),(168,35),(163,120),(185,109),(157,31),(182,68),(180,56),
(191,47),(190,16),(200,1),(196,72),(177,11),(169,12),(191,19),(166,108),(176,23),(189,23),
(197,32),(196,26),(203,2),(199,80),(192,4),(175,49),(197,55),(172,76),(188,99),(195,90),
(200,23),(199,43),(206,3),(202,90),(198,59),(181,58),(200,59),(178,6),(194,35),(198,44),
(203,116),(202,28),(209,4),(205,93),(201,23),(187,10),(203,76),(187,77),(200,78),(204,9),
(209,18),(205,10),(209,5),(208,43),(204,67),(193,54),(206,54),(196,1),(206,7),(207,9),
(212,68),(211,5),(212,6),(211,7),(207,8),(199,9),(209,9),(205,23),(212,111),(213,40);

INSERT INTO reserved_seat (booking_id, seat_id) 
VALUES (1,74),(1,75),(1,76),(2,10),(2,11),(3,100),(3,101),(4,66),(4,67),(5,141),
(6,33),(6,34),(7,102),(7,103),(8,2),(8,3),(8,4),(8,5),(9,98),(9,99),
(10,120),(11,74),(11,75),(11,76),(12,23),(12,24),(13,104),(13,105),(14,60),(14,61),
(15,130),(15,131),(16,71),(16,72),(17,88),(17,89),(18,51),(18,52),(18,53),(18,54),
(19,83),(19,84),(20,138),(21,94),(22,30),(22,31),(22,32),(23,101),(23,102),(23,103),
(23,75),(24,67),(24,68),(25,113),(25,114),(26,44),(27,97),(27,98),(28,71),(29,76),
(30,139),(31,79),(32,50),(33,80),(33,81),(33,82),(33,83),(33,84),(34,62),(34,63),
(35,120),(35,121),(36,16),(36,17),(37,82),(37,83),(38,59),(39,78),(40,111),(40,112),
(41,90),(41,91),(41,92),(42,52),(42,53),(42,54),(43,89),(43,90),(44,71),(44,72),
(45,120),(45,121),(46,38),(46,39),(47,101),(47,102),(48,62),(48,63),(48,64),(49,81),
(50,121),(50,122),(50,123),(50,124),(51,91),(51,92),(51,93),(51,94),(52,61),(52,62),
(53,107),(53,108),(54,67),(54,68),(55,117),(55,118),(56,61),(57,104),(57,105),(57,106),
(58,1),(58,2),(59,75),(59,76),(60,139),(60,140),(61,86),(61,87),(62,13),(62,14),
(63,101),(63,102),(63,103),(64,66),(64,67),(64,68),(65,120),(65,121),(65,122),(65,123),
(66,9),(66,10),(67,76),(68,48),(68,49),(68,50),(69,93),(69,94),(70,121),(70,122),
(71,80),(71,81),(71,82),(72,67),(72,68),(73,77),(73,78),(74,61),(74,62),(74,63),
(75,130),(75,131),(76,9),(76,10),(77,100),(78,57),(79,107),(80,110),(81,91),(81,92),
(82,5),(82,6),(83,104),(83,105),(84,23),(84,24),(85,118),(85,119),(85,120),(85,121),
(86,31),(86,32),(87,76),(87,77),(88,63),(89,89),(89,90),(89,91),(90,113),(90,114),
(91,79),(91,80),(91,81),(92,1),(92,2),(92,3),(92,4),(92,5),(92,6),(93,105),
(94,12),(94,13),(94,14),(95,111),(95,112),(95,113),(95,114),(96,59),(96,60),(96,61),
(97,81),(97,82),(98,47),(98,48),(99,85),(99,86),(100,118),(100,119),(101,103),(101,104),
(102,23),(102,24),(103,81),(103,82),(104,66),(104,67),(105,136),(105,137),(106,39),(106,40),
(107,75),(108,72),(109,101),(109,102),(109,103),(109,104),(109,105),(110,122),(110,123),(110,124),
(111,77),(111,78),(112,8),(113,82),(113,83),(114,56),(114,57),(115,139),(115,140),(116,69),
(117,75),(117,76),(117,77),(118,60),(119,88),(120,111),(121,108),(122,31),(122,32),(123,98),
(124,18),(124,19),(125,120),(125,121),(126,61),(126,62),(127,101),(127,102),(128,70),(128,71),
(129,105),(129,106),(130,141),(130,142),(131,77),(132,71),(133,106),(133,107),(133,108),(134,61),
(135,110),(135,111),(136,52),(137,84),(137,85),(138,37),(139,75),(139,76),(140,140),(140,141),
(141,90),(142,42),(143,94),(143,95),(144,66),(145,112),(146,21),(147,79),(147,80),(147,81),
(148,63),(149,89),(150,128),(150,129),(151,77),(151,78),(151,79),(152,57),(152,58),(152,59),
(153,89),(153,90),(153,91),(154,37),(154,38),(155,111),(155,112),(155,113),(156,19),(156,20),
(157,83),(158,5),(159,77),(160,140),(161,89),(162,9),(163,104),(164,71),(165,117),(165,118),
(166,66),(166,67),(167,83),(167,84),(167,85),(167,86),(168,55),(168,56),(168,57),(168,58),
(169,105),(169,106),(170,121),(170,122),(171,92),(172,36),(173,99),(174,38),(175,117),(175,118),
(176,68),(176,69),(177,81),(177,82),(178,31),(178,32),(179,103),(179,104),(180,119),(181,102),
(182,71),(182,72),(183,100),(183,101),(184,48),(184,49),(185,110),(185,111),(186,12),(186,13),
(187,78),(187,79),(188,39),(189,81),(190,135),(191,79),(192,33),(192,34),(192,35),(193,90),
(194,54),(194,55),(195,121),(195,122),(196,53),(196,54),(197,80),(197,81),(198,2),(198,3),
(199,81),(199,82),(200,115),(201,76),(202,27),(203,103),(203,104),(204,22),(205,140),(206,51),
(207,83),(208,64),(209,98),(210,118),(211,101),(212,61),(212,62),(213,79),(213,80),(214,50),
(215,111),(216,30),(216,31),(217,106),(217,107),(218,40),(218,41),(219,91),(219,92),(220,123),
(221,76),(222,20),(222,21),(223,105),(224,60),(225,133),(225,134),(226,70),(226,71),(226,72),
(227,83),(228,47),(229,80),(230,120),(230,121),(231,85),(231,86),(232,11),(232,12),(232,13),
(233,81),(233,82),(234,62),(234,63),(235,113),(235,114),(236,35),(236,36),(237,81),(237,82),
(238,41),(238,42),(239,77),(239,78),(240,129),(241,102),(241,103),(242,21),(242,22),(242,23),
(243,89),(244,45),(245,137),(245,138),(245,139),(245,140),(246,49),(246,50),(246,51),(247,101),
(248,34),(249,77),(250,138),(251,102),(251,103),(251,104),(252,61),(253,98),(253,99),(254,70),
(255,119),(255,120),(255,121),(256,23),(257,87),(258,36),(258,37),(259,95),(259,96),(260,132),
(261,88),(261,89),(261,90),(262,20),(262,21),(262,22),(262,23),(263,102),(264,63),(265,118),
(266,24),(267,79),(268,68),(269,90),(269,91),(270,120),(271,104),(272,8),(273,76),(274,33),
(275,122),(275,123),(275,124),(276,11),(276,12),(276,13),(277,82),(277,83),(278,27),(278,28),
(279,75),(279,76),(279,77),(280,140),(280,141),(281,80),(282,26),(283,100),(283,101),(283,102),
(284,23),(284,24),(285,121),(286,68),(287,78),(288,4),(288,5),(289,97),(290,113),(290,114),
(291,81),(291,82),(292,49),(293,85),(294,39),(295,140),(296,68),(297,82),(298,25),(298,26),
(299,83),(299,84),(300,134),(300,135),(301,101),(301,102),(302,56),(302,57),(303,92),(303,93),
(304,5),(305,112),(305,113),(306,61),(306,62),(307,81),(308,72),(309,88),(309,89),(310,123),
(311,97),(311,98),(311,99),(312,39),(312,40),(313,79),(314,61),(314,62),(315,144),(316,55),
(317,81),(317,82),(318,66),(319,101),(320,129),(321,83),(321,84),(322,13),(322,14),(322,15),
(323,89),(323,90),(324,37),(324,38),(325,115),(325,116),(326,68),(326,69),(326,70),(327,108),
(328,61),(329,77),(330,125),(330,126),(331,91),(331,92),(332,58),(332,59),(333,76),(333,77),
(334,23),(334,24),(335,131),(335,132),(336,10),(336,11),(337,99),(337,100),(338,67),(338,68),
(339,101),(339,102),(340,138),(341,105),(342,3),(342,4),(343,74),(343,75),(344,58),(344,59),
(345,122),(345,123),(346,34),(346,35),(347,79),(347,80),(348,27),(348,28),(348,29),(349,77),
(350,112),(350,113),(351,83),(351,84),(352,67),(352,68),(352,69),(353,101),(353,102),(353,103),
(354,13),(355,113),(355,114),(356,45),(357,90),(358,70),(359,79),(360,138),(361,97),(362,72),
(363,80),(364,54),(365,122),(366,30),(367,89),(368,48),(369,103),(370,110),(371,88),(372,62),
(373,81),(373,82),(374,59),(375,140),(376,70),(377,93),(378,23),(378,24),(378,25),(378,26),
(379,79),(379,80),(379,81),(380,118),(380,119),(381,103),(382,66),(382,67),(382,68),(382,69),
(383,77),(384,41),(384,42),(384,43),(385,125),(386,68),(387,84),(388,70),(388,71),(389,95),
(390,120),(390,121),(390,122),(391,76),(391,77),(391,78),(392,8),(392,9),(392,10),(393,103),
(394,39),(394,40),(395,127),(395,128),(395,129),(396,66),(396,67),(397,85),(397,86),(398,58),
(399,83),(399,84),(400,118),(400,119),(400,120),(401,103),(401,104),(401,105),(402,51),(403,75),
(404,34),(404,35),(405,109),(405,110),(405,111),(406,72),(407,93),(407,94),(408,49),(409,88),
(410,117),(410,118),(410,119);



/*----------------------------------------------------------------------------------
								AGGREGATE FUNCTIONS

Sirven para hacer calculos con la data. Tenemos:
-COUNT
-SUM
-MAX
-MIN
-AVG
----------------------------------------------------------------------------------*/


SELECT * FROM customers;

/*
Esto me cuenta todos los customers, me da 120
*/
SELECT COUNT(*) FROM customers;

/*
Cuenta todos de la columna last_name, tambien me da 120
*/
SELECT COUNT(last_name) FROM customers;

/*
La misma idea pero ahora me devuelve 114 porque no cuenta los NULL
*/
SELECT COUNT(first_name) FROM customers;

/*
Le puedo imponer condiciones
*/
SELECT COUNT(*) FROM customers
WHERE first_name IS NULL;


SELECT * FROM rooms;

/*
Cuento todos los valores de la columna no_seats asi saco la cantidad todal de asientos de todas las habitaciones
OJO que las columnas que ponga dentro de SUM() tienen que ser coherentes
*/
SELECT SUM(no_seats) FROM rooms;

SELECT SUM(no_seats) FROM rooms
WHERE id > 1;


/*
Saco el maximo de la columna length_min
*/
SELECT MAX(length_min) FROM films;

/*
Analogo con minimo
*/
SELECT MIN(length_min) FROM films;


/*
Saco Promedio
*/
SELECT AVG(length_min) FROM films;

SELECT AVG(length_min) FROM films
WHERE length_min > 120;



SELECT * FROM bookings;

SELECT COUNT(*) FROM bookings b
JOIN screenings s ON b.screening_id = s.id
WHERE customer_id = 10
AND s.start_time LIKE '2017-10%';


SELECT * FROM screenings;
SELECT * FROM films;

SELECT COUNT(*) FROM screenings s
JOIN films f ON s.film_id = f.id
WHERE f.name = 'Blade Runner 2049'
AND s.start_time LIKE '2017-10%';


SELECT * FROM customers;
SELECT * FROM bookings;
SELECT * FROM screenings;


/*
Contar el numero de customers unicos que hayan hecho un booking para octubre 2017
*/
SELECT COUNT(DISTINCT(c.id)) FROM customers c
JOIN bookings b ON c.id = b.customer_id
JOIN screenings s ON s.id = b.screening_id
WHERE s.start_time LIKE '2017-10%';

/*
menos engorroso, menos literal
*/
SELECT COUNT(DISTINCT(customer_id)) FROM bookings b
JOIN screenings s ON s.id = b.screening_id
WHERE s.start_time LIKE '2017-10%';


/*----------------------------------------------------------------------------------*/


SELECT * FROM bookings;

/*
Con esto podes contar la cantidad de bookings por persona
IMPORTANTE: En general cuando querramos contar la cantidad de filas de una tabla hacemos COUNT(id) (la clave primaria es NOT NULL)
y si quiero separar esa cantidad de filas en base a otro campo lo acompañamos con un GROUP BY que tambien sea un id. 
Por ejemplo aca queremos la cantidad de bookings entonces hacemos COUNT(id) FROM bookings pero queremos que los 
cuente para cada persona entonces seleccionamos customer_id
*/
SELECT customer_id, COUNT(id) FROM bookings
GROUP BY customer_id;

/*
Se puede hacer de a varias
*/
SELECT customer_id, screening_id, COUNT(id) FROM bookings
GROUP BY customer_id, screening_id;


SELECT f.name, s.start_time, c.first_name, c.last_name, COUNT(b.id) FROM films f
JOIN screenings s ON f.id = s.film_id
JOIN bookings b ON s.id = b.screening_id
JOIN customers c ON b.customer_id = c.id
GROUP BY f.name, c.last_name, c.first_name, s.start_time
ORDER BY s.start_time;

/*
Si quiero hacer un WHERE para GROUP BY se usa el HAVING
*/
SELECT customer_id, screening_id, COUNT(id) FROM bookings
GROUP BY customer_id, screening_id
HAVING customer_id = 10;



SELECT * FROM bookings;
SELECT * FROM reserved_seat;
SELECT b.customer_id, COUNT(rs.id) FROM bookings b
JOIN reserved_seat rs ON rs.booking_id = b.id
GROUP BY b.customer_id;


SELECT * FROM films;
SELECT * FROM screenings;
SELECT f.name, f.length_min, COUNT(s.id) FROM films f
JOIN screenings s ON s.film_id = f.id
GROUP BY f.name
/*OJO aca la primera vez me daba error porque arriba en el select no puse f.length_min
hay que especificarlo en el select si no no sabe que hacer*/
HAVING f.length_min > 120;


/*----------------------------------------------------------------------------------*/


/*
Existen las subqueries, se pueden usar wn queries de SELECT, INSERT, UPDATE y DELETE
dentro del WHERE o FROM
*/
SELECT id, start_time FROM screenings
WHERE film_id IN
/*Toma del resultado de la subquery*/
	(SELECT id FROM films
    WHERE length_min > 120);

/*NON CORRELATED QUERY, puedo ejecutarlo de forma independiente*/
SELECT id FROM films
WHERE length_min > 120;


SELECT * FROM customers;
SELECT * FROM bookings;

SELECT first_name, last_name, email FROM customers
WHERE id IN
	(SELECT customer_id FROM bookings
    WHERE screening_id = 1);
    
SELECT customer_id FROM bookings
WHERE screening_id = 1;


SELECT * FROM reserved_seat;

SELECT booking_id, COUNT(seat_id) AS no_seats FROM reserved_seat
GROUP BY booking_id;

SELECT AVG(no_seats), MAX(no_seats) FROM
(SELECT booking_id, COUNT(seat_id) AS no_seats FROM reserved_seat
/*DERIVED TABLE la llamo b, se usa para este subquery nomas*/
GROUP BY booking_id) b;


SELECT screening_id, customer_id
FROM bookings
ORDER BY screening_id;

SELECT screening_id, customer_id,
/*esta subquery se ejecuta para cada fila del resultado final!!!*/
(SELECT COUNT(seat_id)
FROM reserved_seat WHERE booking_id = b.id)
FROM bookings b
ORDER BY screening_id;

/*CORRELATED QUERY, no puedo ejecutarlo de forma independiente*/
SELECT COUNT(seat_id)
FROM reserved_seat WHERE booking_id = b.id;



/*Quiero el nombre y duracion de todas las pelis con duracion mayor a la duracion promedio*/
SELECT * FROM films;
SELECT name, length_min FROM films
WHERE length_min > (SELECT AVG(length_min) FROM films);

SELECT AVG(length_min) FROM films;


/*El minimo y maximo numero de proyecciones de una peli particular
Medio ambiguo el enunciado pero lo que quiere es que teniendo el numero de proyecciones separados por pelicula
sacar el minimo numero y el maximo*/
SELECT * FROM screenings;
SELECT MAX(id), MIN(id) FROM 
/*IMPORTANTE: quedan dos campos, film_id y "COUNT(id)" pero no le puedo pedir MAX(COUNT(id)) entonces a COUNT(id) le pongo un alias "id"*/
(SELECT film_id, COUNT(id) AS id FROM screenings
GROUP BY film_id) derived_table;

SELECT film_id, COUNT(id) FROM screenings
GROUP BY film_id;


/*El nombre de cada peli y su numero de proyecciones*/
SELECT * FROM films;
SELECT * FROM screenings;
SELECT f.name, COUNT(s.id) FROM films f
JOIN screenings s ON s.film_id = f.id
GROUP BY f.name;

/*Mejor hagamoslo con subqueries*/
SELECT name,
(SELECT COUNT(id) FROM screenings
WHERE film_id = f.id
)
FROM films f;


/*----------------------------------------------------------------------------------
								MYSQL FUNCTIONS

Son programas guardados a los que les puedo pasar uno o mas parametros y retorna un
valor, por ejemplo los aggregate como  MAX. Hay varios tipos, entre ellos:
-STRING FUNCTIONS (Concatenate, Substring, Upper, Lower. Son un monton)
-DATE FUNCTIONS (Date, Month, Year)
----------------------------------------------------------------------------------*/


/*
CONCATENATE: joinear dos o mas columnas en una sola
*/
/*El problema aca es que literalmente concatena las columnas, queda por ejemplo DavidMay en vez de David May*/
/*OJO que si no le pongo un alias la columna se va a llamar literlamente CONCAT(...)*/
SELECT CONCAT(first_name, last_name) AS full_name FROM customers;

/*Asi quedan separadas por lo que ponga dentro de las comillas*/
SELECT CONCAT(first_name, " ", last_name) AS full_name FROM customers;

SELECT CONCAT(first_name, " ", last_name, " ", email) AS full_name FROM customers;

SELECT CONCAT("This is ", first_name, " ", last_name, "'s email: ", " ", email) AS customers_email FROM customers;


/*
SUBSTRING: Tomamos parte de un string, SUBSTRING("Example",start,length) AS Sub 
Empieza el string nuevo desde el caracter start inclusive y mide length, si no pongo un length sigue hasta el final
Tambien podemos poner un numero negativo en start, empieza desde el final por ejemplo -1 empieza desde la e, -2 la l, -3 la p...
*/
SELECT SUBSTRING("Example",3,3) AS Sub;
SELECT SUBSTRING("Example",3) AS Sub;


SELECT name FROM films;

/*Tambien se puede usar cuando pedimos datos de una tabla*/
SELECT SUBSTRING(name, 1, 3) AS short_name FROM films;

SELECT SUBSTRING(name, 5, 4) AS short_name FROM films;

SELECT SUBSTRING(name, -2, 2) AS short_name FROM films;

SELECT SUBSTRING(name, -6, 5) AS short_name FROM films;


/*
UPPER Y LOWER: Convierten algo en todo mayuscula o minuscula respectivamente
*/
SELECT name FROM rooms;

SELECT UPPER(name) AS name FROM rooms;

SELECT LOWER(name) AS name FROM rooms;



/*Concatenar los nombres de las pelis y longitud de la tabla films*/
SELECT * FROM films;
SELECT CONCAT(name, ": ", length_min) AS film_info FROM films;


/*Extraer el mail del customer del 5 caracter en adelante*/
SELECT * FROM customers;
SELECT SUBSTRING(email,5) AS short_email FROM customers;


/*Seleccionar el primer nombre del customer en minusculas y su apellido en mayusculas para cada customer de apellido Smith*/
SELECT * FROM customers;
SELECT LOWER(first_name) AS first_name, UPPER(last_name) AS last_name FROM customers
WHERE last_name = 'Smith';


/*Seleccionar las ultimas 3 letras de cada nombre de peli de films*/
SELECT * FROM films;
SELECT SUBSTRING(name, -3) AS short_name FROM films
/*para que quede bien*/
ORDER BY id ASC;


/*Concatenar las primeras 3 letras en first_name y last_name de customers*/
SELECT * FROM customers;

SELECT CONCAT(SUBSTRING(first_name, 1, 3), " ", SUBSTRING(last_name, 1, 3)) AS short_full_name FROM customers;



/*
DATE: saca la fecha de un archivo DATE
MONTH: analogo
YEAR: adivina gil
*/
SELECT DATE('2018-06-05 07:45:32') AS Date;

SELECT start_time FROM screenings;
SELECT DATE(start_time) AS Date FROM screenings;

/*Se puede comparar tambien*/
SELECT * FROM screenings;
SELECT * FROM screenings
WHERE DATE(start_time) = '2017-10-03';

SELECT * FROM screenings
WHERE DATE(start_time) BETWEEN '2017-10-03' AND '2017-10-05';

/*Month*/
SELECT MONTH('2018-06-05 07:45:32') AS Month;

SELECT start_time FROM screenings;

SELECT MONTH(start_time) AS Month FROM screenings;

SELECT * FROM screenings
WHERE MONTH(start_time) = 10;

/*Year*/
SELECT YEAR('2018-06-05') AS Year;

SELECT start_time FROM screenings;

SELECT YEAR(start_time) AS Year FROM screenings;

SELECT * FROM screenings
WHERE YEAR(start_time) = '2017';



/*Seleccionar film_id y start_time de screenings con fecha 20/10/17*/
SELECT * FROM screenings;
SELECT film_id, start_time FROM screenings
WHERE DATE(start_time) = '2017-10-20';


/*Seleccionar todo de screenings con fecha entre 6 y 13/10/17*/
SELECT * FROM screenings;
SELECT * FROM screenings
WHERE DATE(start_time) BETWEEN '2017-10-06' AND '2017-10-13';


/*Seleccionar todo de screenings de octubre del 17*/
SELECT * FROM screenings;
SELECT * FROM screenings
WHERE DATE(start_time) LIKE '2017-10%';



/*----------------------------------------------------------------------------------
								CHALLENGES
----------------------------------------------------------------------------------*/



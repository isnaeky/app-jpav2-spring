/*Datos que se insertan a la BD de forma estatica*/
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (1,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (2,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (3,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (4,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (5,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (6,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (7,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (8,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (9,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (10,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (11,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (12,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (13,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (14,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (15,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (16,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (17,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (18,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (19,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (20,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (21,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (22,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (23,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (24,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (25,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (26,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (27,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (28,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (29,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (30,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (31,'Isai', 'Trejo','isnaeky.treiny@gmail.com','2021-08-10','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (32,'Josue', 'Trejo','josue.treiny@gmail.com','2020-01-09','');
INSERT INTO clientes (id,nombre,apellido,email,create_at,foto) VALUES (33,'Santiago', 'Trejo','santiago.treiny@gmail.com','2019-02-01','');




/* Tabla productos*/

INSERT INTO productos (nombre,precio,create_at) VALUES ('Panasonic Pantalla LCD', 20000,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Sony Camara SDE263', 2500,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Apple Ipod nano 4G', 1000,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Mica comoda', 10,NOW());


/*Creacion de facturas*/
INSERT INTO facturas (create_at,descripcion,observacion,cliente_id) VALUES ('2020-02-02', 'Factura oficina', 'Se compra una parte', '1');


INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (2, 1,3);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1, 1,1);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (3, 1,4);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1, 1,2);


INSERT INTO facturas (create_at,descripcion,observacion,cliente_id) VALUES ('2021-02-02', 'Factura Apple', 'Se compra un ipod', '1');
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (2, 2,2);
INSERT INTO facturas_items (cantidad,factura_id,producto_id) VALUES (1, 2,1);









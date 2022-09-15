create table cars (
id serial primary key,
brand varchar(255),
model varchar(255),
price integer
);

insert into cars (brand, model, price) VALUES ('Audi', 'A4', 50000);
insert into cars (brand, model, price) VALUES ('Audi', 'RS7', 115000);
insert into cars (brand, model, price) VALUES ('Lexus', 'IS250', 60000);
insert into cars (brand, model, price) VALUES ('Honda', 'CR-V', 62000);
insert into cars (brand, model, price) VALUES ('Mercedes-Benz', 'E300', 72000);

/**Transaction_1 from MySQL - Read uncommitted Level**/
set session transaction isolation level read uncommitted;
start transaction;
select * from cars;
insert into cars (brand, model, price) VALUES ('Porsche', 'Macan', 125000);
delete from cars where price = 60000;
update cars set price = 67000 where model = 'CR-V';
rollback;
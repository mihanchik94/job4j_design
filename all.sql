create table clients(
id serial primary key,
name varchar (255),
age smallint,
email text);

insert into clients(name, age, email) values('Igor', '21', 'igo@rambler.ru');

update clients set email = 'igor@rambler.ru';

delete from clients;

select* from clients;


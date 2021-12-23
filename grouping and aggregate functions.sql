/*Создание таблиц */
create table devices(
id serial primary key,
name varchar(255),
price float);

create table people(
id serial primary key,
name varchar(255));

create table devices_people(
id serial primary key,
device_id int references devices(id),
people_id int references people(id));

/*Заполнение таблиц */
insert into devices(name, price) values('Alcatel', 4900),('Ihpone', 65000),('Sony', 80000);
insert into people (name) values('Denis'), ('Mikhail'), ('Zinaida');
insert into devices_people(device_id, people_id) values(1, 3), (2, 1), (3, 2);

/*Выводим среднюю стоимость всех устройств */
select avg(price) from devices;

/*Выводим среднюю стоимость устройства каждого человека */
select p.name, avg(d.price) from devices_people devp
join people as p on devp.people_id = p.id
join devices as d on devp.device_id = d.id
group by p.name;

/*Выводим среднюю стоимость устройства каждого человека при условии что цена больше 5000 */
select avg(price) from devices;
select p.name, avg(d.price) from devices_people devp
join people as p on devp.people_id = p.id
join devices as d on devp.device_id = d.id
group by p.name
having avg(d.price) > 5000;
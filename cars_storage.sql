/*Создаем и заполняем таблицы car_body, engine, transmission, car*/
create table car_body(
id serial primary key,
name varchar(255));

create table engine(
id serial primary key,
name varchar(255));

create table gearbox(
id serial primary key,
name varchar(255));

create table car(
id serial primary key,
manufacturer text ,
model text,
body_id int references car_body(id),
engine_id int references engine(id),
gearbox_id int references gearbox(id));

insert into car_body(name) values('sedan'), ('liftback'), ('hatchback'), ('crossover');
insert into engine(name) values('electric'), ('petrol'), ('diesel');
insert into gearbox(name) values('automatic'), ('manual');
insert into car(manufacturer, model, body_id, engine_id, gearbox_id)
values('Audi', 'A7', 2, 3, 1), ('Genesis', 'GV70', 4, 2, 1), ('Jaguar', 'XF', 1, 3, 1);

/*Выводим список всех машин и всех привязанных к ним детали */
select c.manufacturer as Производитель, c.model as Марка, cb.name as Тип_кузова,
e.name as Тип_двигателя, gb.name as КПП
from car c
join car_body cb on c.body_id = cb.id
join engine e on c.engine_id = e.id
join gearbox gb on c.gearbox_id = gb.id;

/*Выводим отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач*/
select cb.name as Тип_кузова
from car_body cb
left join car c on cb.id = c.body_id
where c.body_id is null;

select e.name as Тип_двигателя
from engine e
left join car c on e.id = c.engine_id
where c.engine_id is null;

select gb.name as КПП
from gearbox gb
left join car c on gb.id = c.gearbox_id
where c.gearbox_id is null;
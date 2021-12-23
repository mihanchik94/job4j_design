/*Создание таблиц */
create table type(
id serial primary key,
name text);

create table product(
id serial primary key,
name text,
type_id int references type(id),
expired_date timestamp,
price float);

/*Заполнение таблиц */
insert into type(name) values('Вода'), ('Мясо'), ('Птица'), ('Рыба'), ('Молоко'), ('Сыр');
insert into product(name, type_id, expired_date, price)
values('Шишкин лес', 1, '2022-10-04', 75), ('Архыз', 1, '2022-09-12', 110), 
('Святой источник', 1, '2021-12-07', 80), ('Баранина', 2, '2022-01-02', 2040), 
('Свинина', 2, '2021-12-30', 600), ('Говядина', 2, '2022-01-01', 1600), 
('Курица', 3, '2022-01-03', 400), ('Утка', 3, '2021-12-22', 700), 
('Лосось', 4, '2022-01-01', 1200), ('Тунец', 4, '2022-01-03', 1550), 
('Простоквашино', 5, '2022-05-10', 89), ('Parmalat', 5, '2022-10-10,', 96), 
('Маасдам', 6, '2022-02-14', 700), ('Моцарелла', 6, '2021-11-30', 277), 
('Гауда', 6, '2022-03-01', 362), ('Тильзитер', 6, '2022-01-22', 511);

/*Выводим все продукты с типом 'Сыр' */
select * from product as p
join type as t on t.id = p.type_id
where t.name = 'Сыр';

/*Выводим все продукты, у которых в имени есть слово 'Мороженое' */
select * from product where name like '%Мороженое%';

/*Выводим все продукты, срок годности которых уже истек*/
select * from product where expired_date < Now();

/*Выводим самый дорогой продукт*/
select * from product where price = (select max(price) from product);
select * from product order by price desc limit 1;

/*Выводим для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество*/
select t.name as имя_типа, count(p.name) as количество
from product as p 
join type as t 
on p.type_id = t.id
group by t.name;

/*Выводим все продукты с типом "Сыр" и "Молоко*/
select * from 
product as p
join type as t
on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';

/*Выводим тип продуктов, которых осталось меньше 10 штук*/
select t.name as имя_типа, count(p.name) as количество
from product as p 
join type as t 
on p.type_id = t.id
group by t.name
having count(t.name) < 10;

/*Выводим все продукты и их тип*/
select p.name as имя_продукта, t.name as имя_типа
from product as p
join type as t
on type_id = t.id;

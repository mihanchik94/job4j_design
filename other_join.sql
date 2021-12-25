/*Создаем и заполняем таблицы departments и emploers*/
create table departments(
id serial primary key,
name varchar(255));

create table emploers(
id serial primary key,
name varchar(255),
departments_id int references departments(id));

insert into departments(name) values('Department 1'),('Department 2'),('Department 3'),('Department 4'),('Department 5');
insert into emploers(name, departments_id) values ('Employer 1', 1),('Employer 2', 2),('Employer 3', 3),
('Employer 4', null),('Employer 5', 5);

/*Выполняем запросы с left, right, full, cross соединениями */
/*Left */
select * from emploers e
left join departments d
on e.departments_id = d.id;

/*Right */
select * from emploers e
right join departments d
on e.departments_id = d.id;

/*Full*/
select * from emploers e
full join departments d
on e.departments_id = d.id;

/*Cross*/
select * from emploers e
cross join departments d;

/*Используя left join находим департаменты, у которых нет работников */
select d.name from departments d
left join emploers e
on e.departments_id = d.id
where e.name is null;

/*Используя left и right join пишем запросы, которые давали бы одинаковый результат */
/*Left */
select d.id, d.name, e.id, e.name, e.departments_id from emploers e
left join departments d
on e.departments_id = d.id;

/*Right */
select * from departments d
right join emploers e
on d.id = e.departments_id;

/*Создаем и заполняем таблицу teens с атрибутами name, gender*/
create table teens (
name varchar(255),
gender boolean);

insert into teens(name, gender) values('Olya', true), ('Katya', true), ('Igor', false), ('Dima', false);

/*Используя cross join составляем все возможные разнополые пары */
select (t1.name || ' ' || t2.name) as pair
from teens t1
cross join teens t2
where t1.gender != t2.gender;
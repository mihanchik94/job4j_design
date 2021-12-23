create table lessons(
id serial primary key,
name text);

create table students(
id serial primary key,
name text,
age smallint,
lessons_id int references lessons(id));

insert into lessons(name) values('Guitar classes');
insert into lessons(name) values('Piano classes');
insert into lessons(name) values('Drums classes');

insert into students(name, age, lessons_id) values('Anna', 17, 2);
insert into students(name, age, lessons_id) values('Nikolay', 24, 2);
insert into students(name, age, lessons_id) values('Dmitriy', 38, 1);
insert into students(name, age, lessons_id) values('Olga', 15, 1);
insert into students(name, age, lessons_id) values('Alexey', 18, 1);
insert into students(name, age, lessons_id) values('Rebecca', 15, 3);
insert into students(name, age, lessons_id) values('Evgeniya', 17, 2);
insert into students(name, age, lessons_id) values('Arcadiy', 28, 3);

select st.name, st.age, les.name from students as st join lessons as les on st.lessons_id = les.id;
select st.name as Имя_ученика, st.age as Возраст_ученика, les.name as Название_предмета
from students as st join lessons as les on st.lessons_id = les.id;
select st.name, st.age, les.name from students as st join lessons as les on st.lessons_id = les.id where st.age >= 18;
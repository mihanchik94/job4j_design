create table fauna (
id serial primary key,
name text,
avg_age int,
discovery_date date);

insert into fauna(name, avg_age, discovery_date) values('type: amphibia, name: Platypelis barbouri', 8, date '1940-09-02');
insert into fauna(name, avg_age, discovery_date) values('type: fish, name: Notoraja tobitukai', 4, date '1940-05-02');
insert into fauna(name, avg_age, discovery_date) values('type: fish, name: Silurus soldatovi', 60, date '1948-06-16');
insert into fauna(name, avg_age, discovery_date) values('type: spider, name: Nops toballus', 1, date '1967-03-08');
insert into fauna(name, avg_age, discovery_date) values('type: mammal, name: Nesolagus timminsi', 1, date '2000-07-22');
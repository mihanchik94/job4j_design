/* many-to-one */
create table customers(
id serial primary key,
name varchar(255));

create table orders(
id serial primary key,
customer_ID int references customers(id));

/* many-to-many */
create table pupils(
id serial primary key,
name varchar(255));

create table subjects(
id serial primary key,
name varchar(255));

create table pupils_subjects (
pupil_id int references pupils(id),
subject_id int references subjects(id));

/* one-to-one */
create table passengers(
id serial primary key,
name varchar(255),
lastname varchar(255));

create table tickets(
id serial primary key,
number_of_flight text);

create table passengers_tickets (
id serial primary key,
passenger_id int references passengers(id) unique,
ticket_id int references tickets(id) unique);
/*Transaction 1 Repeatable read level*/
begin transaction isolation level repeatable read;
select * from cars;
insert into cars (brand, model, price) VALUES ('Porsche', 'Macan', 125000);
delete from cars where price = 60000;
update cars set price = 67000 where model = 'CR-V';
rollback;
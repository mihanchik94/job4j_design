/*Transaction 2 Repeatable read level*/
begin transaction isolation level repeatable read;
select * from cars;
update cars set price = 67000 where model = 'CR-V';
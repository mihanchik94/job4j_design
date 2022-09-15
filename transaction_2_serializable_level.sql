/*Transaction 2 Serializable level*/
begin transaction isolation level serializable;
select sum(price) from cars;
update cars set price = 67000 where model = 'CR-V';
commit
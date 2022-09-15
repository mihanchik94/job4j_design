/*Transaction 1 Serializable level*/
begin transaction isolation level serializable;
select sum(price) from cars;
update cars set price = 74000 where model = 'E300';
commit;
/*Start transaction 2*/
begin transaction;
update cars set price = 67000 where model = 'CR-V';
select * from cars;
savepoint first_savepoint;
delete from cars;
drop table cars;
rollback; /*игнорирует savepoint и откатывает таблицу до начального состояния транзакции*/*/
select * from cars;
begin transaction;
delete from cars where price = 60000;
savepoint first_savepoint;
delete from cars where brand = 'Audi';
select * from cars;
rollback to first_savepoint;
select * from cars;
commit;
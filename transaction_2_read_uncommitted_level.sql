/**Transaction_2 from MySQL - Read uncommitted Level**/
set session transaction isolation level read uncommitted;
start transaction;
select * from cars;
select sum(price) from cars;
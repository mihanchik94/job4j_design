/**Создаем триггер, который прибавляет налог на statement-уровне после добавления элементов**/
create or replace function VAT_ST()
returns trigger as
$$
BEGIN
update products
set price = price * 1.2
where id = (select id from inserted);
return new;
END;
$$
Language 'plpgsql';

create trigger VAT_statement
after insert
on products
referencing new table as inserted
for each statement
execute procedure VAT_ST();

/**Создаем триггер, который прибавляет налог на row-уровне до добавления элементов**/
create or replace function VAT_ROW()
returns trigger as
$$
BEGIN
new.price = new.price + new.price * 0.2;
return NEW;
END;
$$
Language 'plpgsql';

create trigger VAT_row
before insert
on products
for each row
execute procedure VAT_ROW();

/**Создаем триггер на row-уровне, который при вставке продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price. **/
create table history_of_price(
	id serial primary key,
	name varchar(50),
    price integer,
    date timestamp
);

create or replace function add_history()
returns trigger as
$$
BEGIN
insert into history_of_price (name, price, date) values (new.name, new.price, current_date);
return NEW;
END;
$$
Language 'plpgsql';

create trigger price_history
after insert
on products
for each row
execute procedure add_history();
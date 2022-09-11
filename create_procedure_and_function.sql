/**Создадим процедуру, которая удаляет запись по id**/
create or replace procedure delete_by_id_proc(data_id integer)
language 'plpgsql'
as
$$
BEGIN
delete from products where id = data_id;
END;
$$;

/**Создадим функцию, которая удаляет запись по id**/
create or replace function delete_by_id_func(data_id integer)
returns void
language 'plpgsql'
as
$$
BEGIN
delete from products where id = data_id;
END;
$$;
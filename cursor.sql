begin transaction;
declare back_prod_cursor cursor for select * from products;

fetch last from back_prod_cursor;
fetch prior from back_prod_cursor;
fetch prior from back_prod_cursor;
move backward 15 from back_prod_cursor;
fetch prior from back_prod_cursor;
fetch prior from back_prod_cursor;
close back_prod_cursor;
commit;
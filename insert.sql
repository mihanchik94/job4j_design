insert into role(role_name) values('Worker');
insert into rules(rule_name) values('some rule');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into users(name, role_id) values('Igor', 1);
insert into category(category_name) values('some category');
insert into state(state_name) values('some state');
insert into item(name, user_id, category_id, state_id) values('some item', 1, 1, 1);
insert into attachs(name_attach, item_id) values('picture.png', 1);
insert into comments(comments_name, item_id) values('some comments', 1);
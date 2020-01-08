
INSERT INTO public.role (id, name) VALUES (1, 'CLIENT');
INSERT INTO public.role (id, name) VALUES (2, 'COOK');
INSERT INTO public.role (id, name) VALUES (3, 'ADMIN');

INSERT INTO public.userentity (id, email, idverification, isVerify, password, role_id ) VALUES (1, '1', 0, true, '1', 2);

INSERT INTO public.cooks (id, birthday, email, mobile, name, password, status, surname, userentity_id) VALUES (1, null, 1, null, null, 1, null, null, 1);

INSERT INTO public.towns (id, name) VALUES (1, 'Минск');
INSERT INTO public.towns (id, name) VALUES (2, 'Брест');
INSERT INTO public.towns (id, name) VALUES (3, 'Витебск');


INSERT INTO public.districts (id, name, town_id) VALUES (1, 'Центральный', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (2, 'Советский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (3, 'Первомайский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (4, 'Партизанский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (5, 'Заводской', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (6, 'Ленинский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (7, 'Октябрьский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (8, 'Московский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (9, 'Фрунзенский', 1);
INSERT INTO public.districts (id, name, town_id) VALUES (10, 'Аркадия', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (11, 'Березовка', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (12, 'Восток', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (13, 'Гершоны', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (14, 'Волынка', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (15, 'Дубровка', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (16, 'Мощенка', 2);
INSERT INTO public.districts (id, name, town_id) VALUES (17, 'Старые Задворцы', 2);



INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (1, 1);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (1, 2);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (1, 4);





INSERT INTO public.cuisines (id, name) VALUES (1, 'Austria');
INSERT INTO public.cuisines (id, name) VALUES (2, 'China');
INSERT INTO public.cuisines (id, name) VALUES (3, 'Georgia');
INSERT INTO public.cuisines (id, name) VALUES (4, 'Turkey');
INSERT INTO public.cuisines (id, name) VALUES (5, 'USA');
INSERT INTO public.cuisines (id, name) VALUES (6, 'Sicily');
INSERT INTO public.cuisines (id, name) VALUES (7, 'Sweden');
INSERT INTO public.cuisines (id, name) VALUES (8, 'France');
INSERT INTO public.cuisines (id, name) VALUES (9, 'Southern United States');
INSERT INTO public.cuisines (id, name) VALUES (10, 'Morocco');
INSERT INTO public.cuisines (id, name) VALUES (11, 'Greece');
INSERT INTO public.cuisines (id, name) VALUES (12, 'Bolivia');
INSERT INTO public.cuisines (id, name) VALUES (13, 'Spain');
INSERT INTO public.cuisines (id, name) VALUES (14, 'Venezuela');
INSERT INTO public.cuisines (id, name) VALUES (15, 'Russia');
INSERT INTO public.cuisines (id, name) VALUES (16, 'Ireland');
INSERT INTO public.cuisines (id, name) VALUES (17, 'Korea');
INSERT INTO public.cuisines (id, name) VALUES (18, 'Germany');
INSERT INTO public.cuisines (id, name) VALUES (19, 'England');
INSERT INTO public.cuisines (id, name) VALUES (20, 'Taiwan');
INSERT INTO public.cuisines (id, name) VALUES (21, 'Jamaica');
INSERT INTO public.cuisines (id, name) VALUES (22, 'Canada');
INSERT INTO public.cuisines (id, name) VALUES (23, 'Abu Dhabi');
INSERT INTO public.cuisines (id, name) VALUES (24, 'Belarus');




INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (1, 'DESSERT', 'Apfelstrudel', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (2, 'DESSERT', 'Baklava', 4);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (3, 'DESSERT', 'Black Forest Cake', 18);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (4, 'DESSERT', 'Borma', 4);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (5, 'DESSERT', 'Brownies', 5);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (6, 'DESSERT', 'Cannoli', 6);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (7, 'DESSERT', 'Cardamom Buns', 7);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (8, 'DESSERT', 'Chocolate Mousse', 8);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (9, 'DESSERT', 'Coconut Cake', 9);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (10, 'DESSERT', 'Cornes de Gazelle', 10);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (11, 'MAIN_COURSE', 'Kinkali', 3);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (12, 'MAIN_COURSE', 'Dumplings', 2);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (13, 'MAIN_COURSE', 'Roast Beef', 19);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (14, 'MAIN_COURSE', 'Hamburger', 5);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (15, 'MAIN_COURSE', 'Beef Noodle Soup', 20);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (16, 'MAIN_COURSE', 'Ackee and Salt fish', 21);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (17, 'MAIN_COURSE', 'Bulgogi', 17);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (18, 'MAIN_COURSE', 'Poutine', 22);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (19, 'MAIN_COURSE', 'Falafel', 23);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (20, 'MAIN_COURSE', 'Pot-au-feu', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (21, 'APPETISER', 'Horiatiki', 11);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (22, 'APPETISER', 'Asaditos', 12);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (23, 'APPETISER', 'Tiropita', 11);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (24, 'APPETISER', 'Croquetas de jamon', 13);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (25, 'APPETISER', 'Pan de jamon', 14);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (26, 'APPETISER', 'Pinchos morunos', 13);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (27, 'APPETISER', 'Blini', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (28, 'APPETISER', 'Boxty', 16);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (29, 'APPETISER', 'Dak kang jung', 17);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (30, 'APPETISER', 'Flammkuchen', 18);






INSERT INTO public.menus (id, luxury_type, name, price) VALUES (1, 'TEMPTATION', 'Catch of the Day', 45.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (2, 'TEMPTATION', 'Decadent Dining', 45.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (3, 'TEMPTATION', 'Winter Warmer', 45.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (4, 'TEMPTATION', 'Country Fine Dining', 45.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (5, 'PRESTIGE', 'Chef Special', 65.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (6, 'PRESTIGE', 'In To The Deep Blue', 65.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (7, 'PRESTIGE', 'Delicious Feast', 65.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (8, 'SIGNATURE', 'Touch Of Indulgence', 95.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (9, 'SIGNATURE', 'Far East', 105.00);
INSERT INTO public.menus (id, luxury_type, name, price) VALUES (10, 'SIGNATURE', 'December', 95.00);






INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (1, 1);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (1, 11);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (1, 21);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (2, 2);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (2, 12);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (2, 22);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (3, 3);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (3, 13);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (3, 23);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (4, 4);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (4, 14);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (4, 24);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (5, 5);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (5, 15);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (5, 25);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (6, 6);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (6, 16);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (6, 26);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (7, 7);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (7, 17);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (7, 27);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (8, 8);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (8, 18);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (8, 28);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (9, 9);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (9, 19);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (9, 29);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (10, 10);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (10, 20);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (10, 30);



INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (1, 1);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (1, 2);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (1, 5);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (1, 7);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (1, 10);



INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (9, 'MONDAY', '23:00', '17:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (10, 'TUESDAY', '21:00', '17:00');

INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (1, 9);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (1, 10);


INSERT INTO public.role (id, name) VALUES (1, 'CLIENT');
INSERT INTO public.role (id, name) VALUES (2, 'COOK');



INSERT INTO public.userentity (id, email, idverification, isverify, password, role_id) VALUES (246, 'kuvshinovI@gmail.com', '9601acd44c8f', true, 'kuvshinovI', 2);
INSERT INTO public.userentity (id, email, idverification, isverify, password, role_id) VALUES (245, 'ravik_huligan@gmail.com', '942910baebcd', true, 'ravik_huligan', 2);
INSERT INTO public.userentity (id, email, idverification, isverify, password, role_id) VALUES (243, 'jamie_oliver@gmail.com', '670f19c9491a', true, 'jamie_oliver', 2);
INSERT INTO public.userentity (id, email, idverification, isverify, password, role_id) VALUES (244, 'gordon_ramsay1999@gmail.com', '69cbb4091c5d', true, 'gordon_ramsay1999', 2);
INSERT INTO public.userentity (id, email, idverification, isverify, password, role_id) VALUES (358, 'yanchelo@gmail.com', '9bd48b666d24', true, 'yanchelo', 1);


INSERT INTO public.clients (id, address, email, mobile, name, password, surname, userentity_id) VALUES (2, 'Лынькова 20, 5 кв.', 'yanchelo@gmail.com', '+375444562354', 'Ян', 'yanchelo', 'Конончук', 358);


INSERT INTO public.cooks (id, address, birthday, city, email, mobile, name, password,  surname, userentity_id) VALUES (28, null, null, null, 'jamie_oliver@gmail.com', '+375293471294', 'Jamie', 'jamie_oliverqwerty', 'Oliver', 243);
INSERT INTO public.cooks (id, address, birthday, city, email, mobile, name, password,  surname, userentity_id) VALUES (29, null, null, null, 'gordon_ramsay1999@gmail.com', '+375258743287', 'Gordon', 'gordon_ramsay1999qwerty', 'Ramsay', 244);
INSERT INTO public.cooks (id, address, birthday, city, email, mobile, name, password, surname, userentity_id) VALUES (30, null, null, null, 'ravik_huligan@gmail.com', '+375293385763', 'Равиль ', 'ravik_huliganqwerty', 'Тенишев', 245);
INSERT INTO public.cooks (id, address, birthday, city, email, mobile, name, password,  surname, userentity_id) VALUES (31, null, null, null, 'kuvshinovI@gmail.com', '+375447846632', 'Илья', 'kuvshinovIqwerty',  'Кувшинов', 246);



INSERT INTO public.towns (id, name) VALUES (1, 'Минск');
INSERT INTO public.towns (id, name) VALUES (2, 'Брест');
INSERT INTO public.towns (id, name) VALUES (3, 'Москва');
INSERT INTO public.towns (id, name) VALUES (4, 'Гродно');
INSERT INTO public.towns (id, name) VALUES (5, 'Гомель');
INSERT INTO public.towns (id, name) VALUES (6, 'Могилев');



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
INSERT INTO public.districts (id, name, town_id) VALUES (18, 'Академический', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (19, 'Алексеевский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (20, 'Алтуфьевский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (21, 'Арбат', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (22, 'Аэропорт', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (23, 'Бабушкинский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (24, 'Басманный', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (25, 'Беговой', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (26, 'Бескудниковский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (27, 'Бибирево', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (28, 'Бирюлёво Восточное', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (29, 'Бирюлёво Западное', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (30, 'Богородское', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (31, 'Братеево', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (32, 'Бутырский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (33, 'Вешняки', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (34, 'Внуково', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (35, 'Войковский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (36, 'Восточное Дегунино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (37, 'Восточное Измайлово', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (38, 'Восточный', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (39, 'Выхино-Жулебино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (40, 'Гагаринский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (41, 'Головинский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (42, 'Гольяново', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (43, 'Даниловский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (44, 'Западное Дегунино	', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (46, 'Зюзино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (47, 'Измайлово', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (48, 'Коньково', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (49, 'Котловка', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (50, 'Лефортово', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (51, 'Лианозово', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (52, 'Марфино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (53, 'Марьино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (54, 'Нагатино-Садовники', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (55, 'Нагорный', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (56, 'Некрасовка', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (57, 'Обручевский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (58, 'Останкинский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (59, 'Отрадное', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (60, 'Печатники', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (61, 'Пресненский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (62, 'Ростокино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (63, 'Савёлки', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (64, 'Свиблово', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (65, 'Таганский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (66, 'Тверской', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (67, 'Филёвский Парк', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (68, 'Фили-Давыдково', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (69, 'Хамовники', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (70, 'Ховрино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (71, 'Царицыно', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (72, 'Черёмушки', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (73, 'Чертаново Южное', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (74, 'Южное Тушино', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (75, 'Южное Бутово', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (76, 'Якиманка', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (77, 'Ярославский', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (45, 'Зюбовка', 3);
INSERT INTO public.districts (id, name, town_id) VALUES (78, 'Берестовицкий', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (79, 'Волковысский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (80, 'Вороновский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (81, 'Гродненский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (82, 'Дятловский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (83, 'Зельвенский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (84, 'Ивьевский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (85, 'Кореличский', 4);
INSERT INTO public.districts (id, name, town_id) VALUES (86, 'Брагинский', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (87, 'Буда-Кошелёвский', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (90, 'Добрушский ', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (93, 'Жлобинский', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (98, 'Дрибинский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (99, 'Кировский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (96, 'Глусский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (94, 'Белыничский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (100, 'Климовичский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (92, 'Житковичский', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (91, 'Вороновский', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (95, 'Быховский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (89, 'Гомельский ', 5);
INSERT INTO public.districts (id, name, town_id) VALUES (97, 'Горецкий', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (101, 'Кличевский', 6);
INSERT INTO public.districts (id, name, town_id) VALUES (88, 'Ветковский ', 5);



INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 5);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 6);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 8);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 7);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 4);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 3);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 2);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 9);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (30, 1);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 5);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 6);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 8);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 7);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 4);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 3);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 2);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 9);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (31, 1);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 5);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 6);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 8);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 7);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 4);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 3);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 2);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 9);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (28, 1);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 5);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 6);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 8);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 7);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 4);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 3);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 2);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 9);
INSERT INTO public.cooks_districts (cook_id, districts_id) VALUES (29, 1);


INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (5, 'MONDAY', '03:00', '17:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (6, 'TUESDAY', '23:00', '17:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (7, 'WEDNESDAY', '23:00', '17:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (8, 'THURSDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (9, 'FRIDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (10, 'SATURDAY', '21:00', '15:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (11, 'SUNDAY', '21:00', '15:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (12, 'MONDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (13, 'TUESDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (14, 'WEDNESDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (15, 'THURSDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (16, 'FRIDAY', '23:00', '12:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (28, 'MONDAY', '23:00', '17:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (29, 'TUESDAY', '23:00', '07:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (30, 'WEDNESDAY', '04:00', '20:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (31, 'THURSDAY', '23:00', '19:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (32, 'MONDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (33, 'TUESDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (34, 'WEDNESDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (35, 'THURSDAY', '22:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (36, 'FRIDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (37, 'SATURDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (38, 'SUNDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (46, 'MONDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (47, 'TUESDAY', '23:00', '10:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (48, 'WEDNESDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (49, 'THURSDAY', '23:00', '11:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (50, 'FRIDAY', '23:00', '09:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (51, 'SATURDAY', '23:00', '08:00');
INSERT INTO public.work_times (id, week_day, end_time, start_time) VALUES (52, 'SUNDAY', '23:00', '06:00');

INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 5);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 6);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 7);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 8);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 9);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 10);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (29, 11);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (28, 12);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (28, 13);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (28, 14);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (28, 15);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (28, 16);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 32);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 33);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 34);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 35);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 36);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 37);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (30, 38);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 46);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 47);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 48);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 49);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 50);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 51);
INSERT INTO public.cooks_work_times (cook_id, worktime_id) VALUES (31, 52);


INSERT INTO public.cuisines (id, name) VALUES (4, 'Турция');
INSERT INTO public.cuisines (id, name) VALUES (15, 'Россия');
INSERT INTO public.cuisines (id, name) VALUES (20, 'Тайвань');
INSERT INTO public.cuisines (id, name) VALUES (19, 'Англия');
INSERT INTO public.cuisines (id, name) VALUES (6, 'Сицилия');
INSERT INTO public.cuisines (id, name) VALUES (1, 'Австрия');
INSERT INTO public.cuisines (id, name) VALUES (21, 'Ямайка');
INSERT INTO public.cuisines (id, name) VALUES (16, 'Ирландия');
INSERT INTO public.cuisines (id, name) VALUES (3, 'Грузия');
INSERT INTO public.cuisines (id, name) VALUES (23, 'Абу Даби');
INSERT INTO public.cuisines (id, name) VALUES (22, 'Канада');
INSERT INTO public.cuisines (id, name) VALUES (12, 'Боливия');
INSERT INTO public.cuisines (id, name) VALUES (24, 'Беларусь');
INSERT INTO public.cuisines (id, name) VALUES (11, 'Греция');
INSERT INTO public.cuisines (id, name) VALUES (10, 'Марокко');
INSERT INTO public.cuisines (id, name) VALUES (14, 'Венесуэла');
INSERT INTO public.cuisines (id, name) VALUES (7, 'Швеция');
INSERT INTO public.cuisines (id, name) VALUES (9, 'Штаты');
INSERT INTO public.cuisines (id, name) VALUES (17, 'Корея');
INSERT INTO public.cuisines (id, name) VALUES (18, 'Германия');
INSERT INTO public.cuisines (id, name) VALUES (8, 'Франция');
INSERT INTO public.cuisines (id, name) VALUES (5, 'США');
INSERT INTO public.cuisines (id, name) VALUES (2, 'Китай');
INSERT INTO public.cuisines (id, name) VALUES (13, 'Испания');
INSERT INTO public.cuisines (id, name) VALUES (25, 'Италия');




INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (247, 'STARTER', '''''Parmigiana di melanzana'''' with pesto sauce (baked aubergines, tomato and mozzarella)', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (248, 'MAIN_COURSE', 'Beef fillet with green pepper sauce, autumn vegetables and slowed cooked potatoes', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (249, 'DESSERT', 'Lemon meringue with chocolate sauce', 2);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (250, 'COCKTAIL', 'Champagne cocktail', 2);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (252, 'APPETISER', 'Courgette cream, grana padano and crispy pancetta', 8);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (253, 'STARTER', 'Oven-gratin scallop with asparagus cream and sweet carrots', 8);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (254, 'MAIN_COURSE', 'Filet de bœuf aux echalotes: 28 day aged beef fillet, served with a shallots sauce, vine tomatoes and traditional ratatouille', 8);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (255, 'DESSERT', 'Mille-feuille with chantilly cream, toasted apricot and melted dark chocolate', 13);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (257, 'STARTER', 'Roast chicken risotto with green peas and asparagus, cooked with fresh organic herbs and served with wild rocket salad', 3);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (258, 'MAIN_COURSE', 'Breaded chicken with grilled cheese, served with salted mushrooms, creamy mash with white truffle and red cabbage slaw', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (259, 'DESSERT', 'Salted caramel cheesecake', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (261, 'APPETISER', 'Pan Fried Gyoza''s, Grilled Asparagu, Vietnamese Summer Roll', 20);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (262, 'STARTER', 'Thai Prawn Salad, 3 Types of Dim Sum', 20);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (263, 'MAIN_COURSE', 'Grilled Chicken with Sweet Potato & teriyaki sauce, Grilled Lamb Chop', 20);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (264, 'DESSERT', 'Exotic Asian Fruits', 20);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (266, 'APPETISER', 'Hibiscus sour with home made gin infused with hibiscus and rose flowers, grapefruit, lemon juice and egg white', 7);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (267, 'APPETISER', 'Crispy arancino with sun-dried tomatoes arabiata sauce, basil, mozzarella and truffle mayo', 7);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (268, 'STARTER', 'Cream of porcini and truffle with Parmesan crostino', 7);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (269, 'MAIN_COURSE', 'Lemon and pesto roasted salmon filet with tagliatelle, creamy white wine sauce, crispy pancetta and steamed clams', 14);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (270, 'DESSERT', 'Hot fudge chocolate brownie with salted caramel sauce and vanilla ice cream', 14);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (272, 'APPETISER', 'Pistachio Crusted Fromage Frais on a Caramelised Almond Tuile with Pickled Strawberry', 18);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (273, 'STARTER', 'Pan-Roasted Cod Loin with Pea and Asparagus Risotto', 18);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (274, 'DESSERT', 'Dark Chocolate and Ginger Silk Mousse with Honeycomb and Raspberry Sorbet', 16);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (276, 'STARTER', 'Tartlet of baby beetroot with red onion marmalade topped with a mature cheddar bechamel sauce', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (277, 'MAIN_COURSE', 'Salmon and haddock fishcake with a soft poached egg and lemon and chive butter', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (278, 'DESSERT', 'Triple chocolate brownie served with white chocolate sauce and vanilla bean ice cream', 18);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (280, 'STARTER', 'British Asparagus salad with hollandaise sauce', 19);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (281, 'STARTER', 'Selection of chefs choice of canapés', 19);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (282, 'MAIN_COURSE', 'Roasted Sirloin of Yorkshire beef, served with potato and onion gratin, Bearnaise sauce, roasted cherry tomatoes on the vine and green bens with garlic', 13);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (283, 'DESSERT', 'Raspberry tart with almonds and vanilla creme fraiche', 13);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (285, 'STARTER', 'Duck Breast and Citrus Salad, Asparagus and Forest Fruit Vinaigrette', 14);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (286, 'MAIN_COURSE', 'Slow Roast Corn Fed Chicken Breast, Cauliflower Puree, Fondant Potato served with Spring Vegetables and Wild Mushroom Cream Sauce', 11);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (287, 'DESSERT', 'Dark chocolate delice, Whisky Chantilly Cream and Variations of Berries&Apple, Cinnamon and Muscovado', 14);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (289, 'APPETISER', 'Chef''s seasonal selection & inspiration', 21);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (290, 'STARTER', 'Your choice, to be discussed with the chef', 10);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (291, 'MAIN_COURSE', 'Your choice, to be discussed with the chef', 20);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (292, 'DESSERT', 'Your choice, to be discussed with the chef', 4);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (294, 'APPETISER', 'Chef''s Seasonal Selection & Inspiration', 12);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (295, 'STARTER', 'Roasted Celeriac Soup | Aromatic Croutons | Confit Tomato Petals | Gremolata (V)', 12);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (296, 'MAIN_COURSE', '5-hour Braised Lamb Shoulder | Spinach & Leek Puree | Green Beans | Glazed Baby Carrots | Rosemary Infused Jus', 13);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (297, 'DESSERT', 'Dark Chocolate Fondant | Vanilla Creme Anglaise | Mixed Berry Sauce', 14);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (299, 'APPETISER', 'Selection of Seasonal Canapés (Optional vegetarian)', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (300, 'STARTER', 'Venison Tartare | Pickled Shallots | Crispy Quails Egg | Sautéed Baby Spinach', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (301, 'MAIN_COURSE', 'Caramelised Onion and Goat''s Cheese Tartlet | Roast Seasonal Vegetables | Truffle Honey | Balsamic Syrup', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (302, 'DESSERT', 'Whipped Cream | Toasted Oatmeal soaked in Whisky | Fresh Raspberries | Honey', 1);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (304, 'APPETISER', 'Marinated Olives (V) Padron Peppers (V) Croquetas de Jamon', 22);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (305, 'MAIN_COURSE', 'Tortilla Espanola - Spanish Omelette (V) Deep Fried Calamari | Aioli Iberico Pork Smoked Chorizo Vegetable croquettes (V) Patatas Bravas - Spicy Fried Potatoes (V)', 23);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (306, 'DESSERT', 'Cheesecake', 9);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (308, 'STARTER', 'Семга свежеприсоленная', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (309, 'STARTER', 'Скумбрия холодного копчения с отварным картофелем', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (310, 'STARTER', 'Грудинка свиная печёная соломой жжёная', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (311, 'MAIN_COURSE', 'Горбуша, тушёная с овощами. Поджарка', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (312, 'COCKTAIL', ' Квас (домашний)', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (314, 'STARTER', 'Маслята', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (315, 'MAIN_COURSE', 'Драники картофельные со сметаной', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (316, 'MAIN_COURSE', 'Колоб, запечённый с боровиками, томленными в деревенской сметане', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (317, 'DESSERT', 'Сырники со сметаной и вишнёвым соусом', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (319, 'STARTER', 'Омлет со сладкими помидорами и сулугуни', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (320, 'STARTER', 'Оливье с копченой уткой', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (321, 'STARTER', 'Сельдь под шубой', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (322, 'MAIN_COURSE', 'Самолепные пельмени из сочной свинины с домашней сметаной', 15);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (323, 'DESSERT', 'Блинчики тонкие ажурные со сметаной и мёдом', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (330, 'STARTER', 'Багет с хумосом ', 3);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (331, 'MAIN_COURSE', 'Драники картофельные со сметаной', 24);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (333, 'APPETISER', '1', 23);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (335, 'APPETISER', '12', 23);
INSERT INTO public.dishes (id, dish_type, name, cuisine_id) VALUES (336, 'APPETISER', '12', 23);






INSERT INTO public.menus (id, name, price) VALUES (251, 'Chef Special', 65.00);
INSERT INTO public.menus (id, name, price) VALUES (256, 'Le Parisien', 55.00);
INSERT INTO public.menus (id, name, price) VALUES (260, 'Favorite Flavours', 66.00);
INSERT INTO public.menus (id, name, price) VALUES (265, 'Pan-Asian Menu', 40.00);
INSERT INTO public.menus (id, name, price) VALUES (271, 'Decadence...', 80.00);
INSERT INTO public.menus (id, name, price) VALUES (275, 'Modern Twist', 42.00);
INSERT INTO public.menus (id, name, price) VALUES (279, 'Deluxe', 60.00);
INSERT INTO public.menus (id, name, price) VALUES (284, 'British Terroir', 47.00);
INSERT INTO public.menus (id, name, price) VALUES (288, 'Humble', 39.00);
INSERT INTO public.menus (id, name, price) VALUES (293, 'Bespoke 4 Course Menu', 67.00);
INSERT INTO public.menus (id, name, price) VALUES (298, 'Winter Warmer', 60.00);
INSERT INTO public.menus (id, name, price) VALUES (303, 'Country Fine Dining', 33.00);
INSERT INTO public.menus (id, name, price) VALUES (307, 'Tapas Temptation', 33.00);
INSERT INTO public.menus (id, name, price) VALUES (313, 'Русское подворье', 56.00);
INSERT INTO public.menus (id, name, price) VALUES (318, 'У бабушки в деревне', 49.00);
INSERT INTO public.menus (id, name, price) VALUES (324, 'Иван Да Марья', 60.00);








INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (251, 247);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (251, 248);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (251, 249);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (251, 250);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (256, 252);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (256, 253);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (256, 254);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (256, 255);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (260, 257);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (260, 258);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (260, 259);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (265, 261);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (265, 262);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (265, 263);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (265, 264);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (271, 266);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (271, 267);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (271, 268);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (271, 269);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (271, 270);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (275, 272);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (275, 273);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (275, 274);


INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (279, 276);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (279, 277);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (279, 278);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (284, 280);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (284, 281);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (284, 282);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (284, 283);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (288, 285);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (288, 286);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (288, 287);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (293, 289);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (293, 290);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (293, 291);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (293, 292);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (298, 294);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (298, 295);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (298, 296);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (298, 297);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (303, 299);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (303, 300);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (303, 301);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (303, 302);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (307, 304);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (307, 305);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (307, 306);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (313, 308);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (313, 309);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (313, 310);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (313, 311);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (313, 312);

INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (318, 314);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (318, 315);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (318, 316);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (318, 317);


INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (324, 319);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (324, 320);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (324, 321);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (324, 322);
INSERT INTO public.menus_dishes (menu_id, dishes_id) VALUES (324, 323);




INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (29, 251);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (29, 256);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (29, 260);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (29, 265);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (29, 271);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (28, 275);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (28, 279);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (28, 284);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (28, 288);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (28, 293);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (30, 298);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (30, 303);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (30, 307);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (31, 313);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (31, 318);
INSERT INTO public.cooks_menus (cook_id, menu_id) VALUES (31, 324);




INSERT INTO public.orders (id, addinfo, address, countofguests, date, eventtime, ordertime, order_type, client_id, cook_id, district_id, menu_id) VALUES (4, '', 'Лынькова 20, 5 кв.', 2, '2020-02-15', '19:00', '00:00', 'IN_PROCESS', 2, 31, 1, 324);
INSERT INTO public.orders (id, addinfo, address, countofguests, date, eventtime, ordertime, order_type, client_id, cook_id, district_id, menu_id) VALUES (5, '', 'Лынькова 20, 5 кв.', 2, '2020-02-16', '18:00', '00:00', 'NEW', 2, 31, 1, 318);
INSERT INTO public.orders (id, addinfo, address, countofguests, date, eventtime, ordertime, order_type, client_id, cook_id, district_id, menu_id) VALUES (2, '', 'Лынькова 20, 5 кв.', 3, '2020-02-14', '19:00', '00:00', 'CLOSED', 2, 31, 4, 313);
INSERT INTO public.orders (id, addinfo, address, countofguests, date, eventtime, ordertime, order_type, client_id, cook_id, district_id, menu_id) VALUES (3, '', 'Лынькова 20, 5 кв.', 2, '2020-02-14', '19:00', '00:00', 'CLOSED', 2, 31, 1, 318);
INSERT INTO public.orders (id, addinfo, address, countofguests, date, eventtime, ordertime, order_type, client_id, cook_id, district_id, menu_id) VALUES (6, '', 'Лынькова 20, 5 кв.', 1, '2020-02-14', '19:00', '00:00', 'CLOSED', 2, 31, 1, 313);



INSERT INTO public.clients_orders (client_id, orders_id) VALUES (2, 2);
INSERT INTO public.clients_orders (client_id, orders_id) VALUES (2, 3);
INSERT INTO public.clients_orders (client_id, orders_id) VALUES (2, 4);
INSERT INTO public.clients_orders (client_id, orders_id) VALUES (2, 5);
INSERT INTO public.clients_orders (client_id, orders_id) VALUES (2, 6);



INSERT INTO public.cooks_orders (cook_id, orders_id) VALUES (31, 2);
INSERT INTO public.cooks_orders (cook_id, orders_id) VALUES (31, 3);
INSERT INTO public.cooks_orders (cook_id, orders_id) VALUES (31, 4);
INSERT INTO public.cooks_orders (cook_id, orders_id) VALUES (31, 5);
INSERT INTO public.cooks_orders (cook_id, orders_id) VALUES (31, 6);






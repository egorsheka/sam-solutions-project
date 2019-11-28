# sam-solutions-project
Для запуска проекта необходимо: разместить war-архив на tomcat, скачать и установить последний postgresql,
в файле src/main/resources/db.properties поставить свой логин и пароль от базы.
После запуска проекта и автоматического создания таблиц в базе данных надо за-INSERT-ить данные с файла
src/main/resources/scripts_for_review.sql.

В проекте недостает хорошего контролера, потому что сейчас не хватает знаний в front-end, поэтому
отсутствует DTO. Это всё подтяну и исправлю.

На самом front-end имеется 3 ссылки для работы с данными

Для всех ссылок написан сервис и имеются записи в бд. Единственная недоработка — не отображает сохранённые данные
по ссылке working hours, но можно проверить записанные данные в таблицах cooks_work_times и work_times.

Сам проект называется "повар на дом", поэтому следующая логика:
по ссылке working hours повар выбирает дни, когда может работать и время соответственно
по ссылке location выбирает город и районы (проект по Беларуси)
по ссылке menus повар составляет свои личные меню, которые состоя из блюд(Dish)

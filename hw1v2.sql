CREATE SCHEMA service;

/*Жизненный цикл блюда или заказа*/
CREATE TYPE service.DISH_ORDER_STATUS AS ENUM('IN_QUEUE', 'IN_PROCESS', 'PREPARED', 'ISSUED');

/*Таблица Должности*/
CREATE TABLE service.employee_posts (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	post_name CHARACTER VARYING(20) NOT NULL
);

/*Таблица Сотрудники*/
CREATE TABLE service.employees (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	last_name CHARACTER VARYING(20) NOT NULL,
	first_name CHARACTER VARYING(20) NOT NULL,
	date_of_birth DATE,
	telephone CHARACTER VARYING(20),
	post_id INTEGER NOT NULL REFERENCES service.employee_posts(id),
	salary REAL
);

/*Таблица ингредиентов*/
CREATE TABLE service.ingredients (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	ingredient_name CHARACTER VARYING(20) NOT NULL
);

/*Таблица категорий блюд*/
CREATE TABLE service.dish_categories (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	category_name CHARACTER VARYING(20) NOT NULL
);

/*Таблица возможных блюд*/
CREATE TABLE service.dishes (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	dish_name CHARACTER VARYING(20) NOT NULL,
	category INTEGER NOT NULL REFERENCES service.dish_categories(id),
	price DECIMAL,
	weight DECIMAL
);

/*Вспомагательная таблица соответствия ингредиенотв блюдам*/
CREATE TABLE service.dish_composition (
	dish_id INTEGER NOT NULL REFERENCES service.dishes(id),
	ingredient_id INTEGER NOT NULL REFERENCES service.ingredients(id),
	PRIMARY KEY (dish_id, ingredient_id)
);

/*Склад*/
CREATE TABLE service.warehouse (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	ingredient_id INTEGER NOT NULL REFERENCES service.ingredients(id),
	amount DECIMAL NOT NULL
);

/*Заказы*/
CREATE TABLE service.orders (
	id SERIAL PRIMARY KEY NOT NULL,
	employee_id INTEGER NOT NULL REFERENCES service.employees(id),
	desk INTEGER NOT NULL,
	status service.DISH_ORDER_STATUS NOT NULL,
	order_date DATE
);

/*Вспомагательная таблица соответствия блюд заказам*/
CREATE TABLE service.order_composition (
	order_id INTEGER NOT NULL REFERENCES service.orders(id),
	dish_id INTEGER NOT NULL REFERENCES service.dishes(id),
	PRIMARY KEY (order_id, dish_id)
);

/*Таблица различных меню*/
CREATE TABLE service.menu (
	id SMALLSERIAL PRIMARY KEY NOT NULL,
	menu_name CHARACTER VARYING(20) NOT NULL
);

/*Вспомагательная таблица соответствия блюд меню*/
CREATE TABLE service.menu_composition (
	menu_id INTEGER NOT NULL REFERENCES service.menu(id),
	dish_id INTEGER NOT NULL REFERENCES service.dishes(id),
	PRIMARY KEY (menu_id, dish_id)
);
 
/*Таблица заказаных блюд и их статус*/
CREATE TABLE service.kitchen (
	id SERIAL NOT NULL PRIMARY KEY,
	dish_id INTEGER NOT NULL REFERENCES service.dishes(id),
	employee_id INTEGER NOT NULL REFERENCES service.employees(id),
	order_id INTEGER NOT NULL REFERENCES service.orders(ID),
	status_of_dish service.DISH_ORDER_STATUS NOT NULL,
	date_of_cooked DATE
);

INSERT INTO service.employee_posts (post_name) VALUES ('Директор');
INSERT INTO service.employee_posts (post_name) VALUES ('Аминистратор');
INSERT INTO service.employee_posts (post_name) VALUES ('Официант');
INSERT INTO service.employee_posts (post_name) VALUES ('Шефповар');
INSERT INTO service.employee_posts (post_name) VALUES ('Повар');
INSERT INTO service.employee_posts (post_name) VALUES ('Охрана');

INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Нульпоинтер', 'Ексепшин', '1992-11-24', '(097)444-55-66', (SELECT id FROM service.employee_posts WHERE post_name = 'Директор'), 50000);

INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Иванов', 'Пётр', '1990-10-23', '(097)333-44-55', (SELECT id FROM service.employee_posts WHERE post_name = 'Аминистратор'), 30000);

INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Петров', 'Иван', '1988-05-05', '(067)123-53-45', (SELECT id FROM service.employee_posts WHERE post_name = 'Шефповар'), 30000);

INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Болконский', 'Андрей', '1990-05-14', '(093)453-45-67', (SELECT id FROM service.employee_posts WHERE post_name = 'Повар'), 12000);
INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Курагина', 'Елена', '1980-10-01', '(093)355-45-36', (SELECT id FROM service.employee_posts WHERE post_name = 'Повар'), 10000);

INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Курагин', 'Василий', '1992-02-29', '(050)753-00-25', (SELECT id FROM service.employee_posts WHERE post_name = 'Официант'), 6000);
INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Ростова', 'Наталья', '1989-12-31', '(066)453-77-88', (SELECT id FROM service.employee_posts WHERE post_name = 'Официант'), 5000);
INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Безухов', 'Пьер', '1995-03-08', '(096)123-45-78', (SELECT id FROM service.employee_posts WHERE post_name = 'Официант'), 5000);

INSERT INTO service.employees (last_name, first_name, date_of_birth, telephone, post_id, salary)
		VALUES ('Денисов', 'Василий', '1975-06-26', '(097)444-55-66', (SELECT id FROM service.employee_posts WHERE post_name = 'Охрана'), 3500);

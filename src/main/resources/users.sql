CREATE TABLE users (
  username VARCHAR(60) NOT NULL PRIMARY KEY ,
  password VARCHAR(60) NOT NULL ,
  enabled BOOLEAN NOT NULL ,
  name VARCHAR(60),
  surbame VARCHAR(60)
);

CREATE TABLE roles(
  authority_role VARCHAR(60) NOT NULL PRIMARY KEY
);

CREATE TABLE persistent_logins(
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) NOT NULL PRIMARY KEY,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL
);

CREATE TABLE groups(
  id SERIAL NOT NULL PRIMARY KEY,
  group_name VARCHAR(60)
);

CREATE TABLE group_members(
  id SERIAL NOT NULL PRIMARY KEY,
  username VARCHAR(60) NOT NULL,
  group_id INTEGER NOT NULL REFERENCES users.groups(id)
);

CREATE TABLE group_authorities(
  group_id INTEGER NOT NULL REFERENCES users.groups(id),
  authority VARCHAR(60) NOT NULL REFERENCES users.roles(authority_role)
);

-- data

INSERT INTO users.roles VALUES ('ROLE_ADMIN');
INSERT INTO users.roles VALUES ('ROLE_HR_DEPARTMENT');
INSERT INTO users.roles VALUES ('ROLE_WAITER');
INSERT INTO users.roles VALUES ('ROLE_COOK');
INSERT INTO users.roles VALUES ('ROLE_ACCOUNTANT');
INSERT INTO users.roles VALUES ('ROLE_USER');

INSERT INTO users.users VALUES ('admin', '$2a$06$Yifm6gKAWBmEPkohuO/vvObbrrxEWeWsRFPoAl9Tn6Na/nZzQsuou', TRUE);
INSERT INTO users.users VALUES ('user', '$2a$06$LJWlPcPcsWFsPGUgXMMvZ.buI21066WpmVzTkrSkqIqcqJInkVYVu', TRUE, 'Ivan', 'Petrov');

INSERT INTO users.groups (group_name) VALUES ('Administrators');
INSERT INTO users.groups (group_name) VALUES ('Service managers');
INSERT INTO users.groups (group_name) VALUES ('Office managers');
INSERT INTO users.groups (group_name) VALUES ('Users');

INSERT INTO users.group_authorities VALUES (1, 'ROLE_ADMIN');
INSERT INTO users.group_authorities VALUES (1, 'ROLE_HR_DEPARTMENT');
INSERT INTO users.group_authorities VALUES (1, 'ROLE_WAITER');
INSERT INTO users.group_authorities VALUES (1, 'ROLE_COOK');
INSERT INTO users.group_authorities VALUES (1, 'ROLE_ACCOUNTANT');
INSERT INTO users.group_authorities VALUES (1, 'ROLE_USER');

INSERT INTO users.group_authorities VALUES (2, 'ROLE_WAITER');
INSERT INTO users.group_authorities VALUES (2, 'ROLE_COOK');
INSERT INTO users.group_authorities VALUES (2, 'ROLE_USER');

INSERT INTO users.group_authorities VALUES (3, 'ROLE_HR_DEPARTMENT');
INSERT INTO users.group_authorities VALUES (3, 'ROLE_ACCOUNTANT');
INSERT INTO users.group_authorities VALUES (3, 'ROLE_USER');

INSERT INTO users.group_authorities VALUES (4, 'ROLE_USER');

INSERT INTO group_members (username, group_id) VALUES ('admin', 1);
INSERT INTO group_members (username, group_id) VALUES ('user', 4);
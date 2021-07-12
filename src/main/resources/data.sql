INSERT INTO users (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('AFAltair@yandex.ru', 'Andrew', 'Alenkin', 'admin'),
       ('Irklaser@yandex.ru', 'Noname', 'Unknown', 'user');

INSERT INTO user_role (ROLE, USER_ID)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 1),
       ('ROLE_USER', 2);

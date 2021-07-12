INSERT INTO users (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('afaltair@yandex.ru', 'Andrew', 'Alenkin', '{noop}admin'),
       ('irklaser@yandex.ru', 'Noname', 'Unknown', '{noop}user');

INSERT INTO user_role (ROLE, USER_ID)
VALUES ('ROLE_USER', 1),
       ('ROLE_ADMIN', 1),
       ('ROLE_USER', 2);

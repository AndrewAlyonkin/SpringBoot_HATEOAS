INSERT INTO users (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('admin@gmail.ru', 'Noname', 'Unknown', '{noop}password'),
       ('user@gmail.com', 'Andrew', 'Alenkin', '{noop}admin');


INSERT INTO user_role (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

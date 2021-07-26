create table categories (id bigserial primary key, title varchar(255));
insert into categories (title)
values
('Продукты'),
('Инструмент');

create table products (id bigserial primary key, title varchar(255), price float, category_id bigint REFERENCES categories (id));
insert into products (title, price, category_id)
values
('Хлеб', 24, 1),
('Молоко', 65, 1),
('Сыр', 320, 1),
('Макароны', 100, 1),
('Колбаса', 150, 1);
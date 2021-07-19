create table products (id bigserial primary key, name varchar(255), price float);
insert into products (name, price)
values
('Хлеб', 24),
('Молоко', 65),
('Сыр', 320),
('Макароны', 100),
('Колбаса', 150);
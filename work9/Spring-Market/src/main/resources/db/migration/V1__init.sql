create table products (id bigserial primary key, title varchar(255), price float);
insert into products (title, price)
values
('Хлеб', 24),
('Молоко', 65),
('Сыр', 320),
('Макароны', 100),
('Колбаса', 150);
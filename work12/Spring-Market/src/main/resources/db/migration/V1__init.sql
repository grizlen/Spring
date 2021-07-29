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

create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password)
values
('user', '$2a$10$ADmj6iA2cgUiZIMTQ.ZbrezjSvEWNPeDMXVTnpMFWI4W7h2flFufa')
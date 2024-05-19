DROP TABLE IF EXISTS portfolios_composition CASCADE;
DROP TABLE IF EXISTS bonds_composition  CASCADE;
DROP TABLE IF EXISTS composition CASCADE;
DROP TABLE IF EXISTS bonds  CASCADE;
DROP TABLE IF EXISTS portfolios CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
    user_id      BIGSERIAL PRIMARY KEY,
    birth_date   DATE,
    email        VARCHAR(40) NOT NULL,
    first_name   VARCHAR(40) NOT NULL,
    last_name    VARCHAR(40) NOT NULL,
    login        VARCHAR(40) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    UNIQUE (email,login)   
);

CREATE TABLE portfolios (
    portfolio_id BIGSERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id),
    portfolio_name VARCHAR(40) NOT NULL
);

CREATE TABLE bonds (
    bond_id BIGSERIAL PRIMARY KEY,
    bond_name VARCHAR(255) NOT NULL,
    coupon_payment_date DATE NOT NULL,
	nominal INTEGER NOT NULL,
	coupon_size INTEGER NOT NULL,
	number_of_coupon_periods INTEGER NOT NULL,
	bond_duration INTEGER NOT NULL,
	maturity INTEGER NOT NULL,
	UNIQUE (bond_name)
);

CREATE TABLE composition (
	composition_id BIGSERIAL PRIMARY KEY,
    portfolio_id INTEGER null REFERENCES portfolios(portfolio_id),
    bond_id INTEGER null REFERENCES bonds(bond_id),
	bond_share DOUBLE PRECISION NOT NULL
);

insert into users(email, first_name, last_name, login, password)
values ('georgy@mail.ru', 'Georgy', 'Yakushchev', 'georgy@mail.ru', 'a366153c7dbfbb7dfd5f4298f5dcf409888aa300f20b0ae98c67caeb31040848');
insert into users(email, first_name, last_name, login, password)
values ('gera@mail.ru', 'Gera', 'Yakushchev', 'gera@mail.ru', 'a366153c7dbfbb7dfd5f4298f5dcf409888aa300f20b0ae98c67caeb31040848');
insert into users(email, first_name, last_name, login, password)
values ('georg@mail.ru', 'Georg', 'Yakushchev', 'georg@mail.ru', 'a366153c7dbfbb7dfd5f4298f5dcf409888aa300f20b0ae98c67caeb31040848');

insert into bonds(bond_name, coupon_payment_date, nominal, coupon_size, number_of_coupon_periods, bond_duration, maturity)
values ('SU26223RMFS6', '2024-02-28', 1000, 32, 2, 47, 45),
('SU26227RMFS7', '2024-01-17', 1000, 37, 2, 180, 185),
('SU26222RMFS8', '2024-04-17', 1000, 35, 2, 271, 276),
('SU26234RMFS3', '2024-01-17', 1000, 22, 2, 524, 549),
('SU26229RMFS3', '2024-05-15', 1000, 36, 2, 630, 668),
('SU26219RMFS4', '2024-03-20', 1000, 39, 2, 876, 976),
('SU26226RMFS9', '2024-04-10', 1000, 40, 2, 895, 997),
('SU26207RMFS9', '2024-02-07', 1000, 35, 2, 970, 1116);

insert into portfolios(user_id, portfolio_name)
values (1, 'SberPort'),
(1, 'YandexPort'),
(2, 'SberPort');

insert into composition(portfolio_id, bond_id, bond_share)
values(1, 1, 42.5),
(1, 2, 38.5),
(1, 3, 19),
(2, 1, 40),
(2, 2, 40),
(2, 3, 20),
(3, 1, 15),
(3, 2, 10),
(3, 3, 75);
create table customer (cust_id int check(cust_id >= 100 AND cust_id <= 10000), cust_name varchar(35), annual_revenue int default 20000, cust_type varchar(20) check(cust_type = 'manufacturer' OR cust_type = 'wholesaler' OR cust_type = 'retailer'), PRIMARY KEY (cust_id));
insert into customer (cust_id, cust_name, annual_revenue, cust_type) values (100, 'Revathi', 1000000, 'manufacturer');
insert into customer (cust_id, cust_name, annual_revenue, cust_type) values (101, 'Richa', 1800000, 'wholesaler');
insert into customer (cust_id, cust_name, annual_revenue, cust_type) values (102, 'Rishi', 1000000, 'retailer');
insert into customer (cust_id, cust_name, annual_revenue, cust_type) values (103, 'Rijesh', 4000000, 'wholesaler');
insert into customer (cust_id, cust_name, annual_revenue, cust_type) values (104, 'Kalyan', 4800000, 'wholesaler');
insert into customer (cust_id, cust_name, annual_revenue, cust_type) values (311, 'Karthik', 5500000, 'retailer');

create table truck (`truck_#` int auto_increment, driver_name varchar(20), PRIMARY KEY (`truck_#`));
alter table truck auto_increment=100;
insert into truck (driver_name) values ('Jensen');
insert into truck (driver_name) values ('Sasi');
insert into truck (driver_name) values ('Hrithik');
insert into truck (driver_name) values ('Jake Stinson');

create table city (city_name varchar(30), population bigint, PRIMARY KEY (city_name));
insert into city (city_name, population) values ('London', 100000000);
insert into city (city_name, population) values ('Paris', 120000000);
insert into city (city_name, population) values ('Rome', 200000000);
insert into city (city_name, population) values ('Panama City', 1230000000);
insert into city (city_name, population) values ('San Francisco', 20000000);
insert into city (city_name, population) values ('Sioux City', 5000000000);
insert into city (city_name, population) values ('Manhattan', 10000000);
insert into city (city_name, population) values ('Los Angeles', 7000);
insert into city (city_name, population) values ('Baltimore', 2000);
insert into city (city_name, population) values ('Denver', 1000);
insert into city (city_name, population) values ('St. Louis', 5000);

create table shipment (`shipment_#` int auto_increment, cust_id int check(cust_id >= 100 AND cust_id <= 10000), weight float default 10 check(weight < 1000), `truck_#` int, destination varchar(30), ship_date date, PRIMARY KEY (`shipment_#`), FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE, FOREIGN KEY (`truck_#`) REFERENCES truck(`truck_#`) ON DELETE SET NULL, FOREIGN KEY (destination) REFERENCES city (city_name) ON DELETE SET NULL);
alter table shipment auto_increment=100;
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (100, 500, 100, 'London', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (101, 100, 102, 'Paris', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (101, 300, 103, 'London', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (101, 10, 102, 'Panama City', '2003-12-12');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (101, 20, 101, 'Los Angeles', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (102, 200, 102, 'Rome', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (100, 50, 101, 'Sioux City', '2003-09-18');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (104, 500, 100, 'Manhattan', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (103, 50, 103, 'San Francisco', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (104, 25, 101, 'San Francisco', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (102, 200, 103, 'London', '1998-10-11');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (103, 100, 101, 'London', '1999-09-09');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (104, 500, 100, 'London', '1988-06-18');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (104, 200, 100, 'London', '1998-10-11');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (104, 50, 103, 'Manhattan', '2003-05-29');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (100, 75, 103, 'Los Angeles', '2002-09-17');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (101, 55, 102, 'Baltimore', '2002-07-01');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (103, 45, 101, 'Paris', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (103, 45, 100, 'Rome', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (103, 45, 100, 'Los Angeles', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (104, 45, 102, 'London', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (100, 150, 102, 'Sioux City', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (101, 500, 102, 'Manhattan', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (102, 250, 102, 'San Francisco', '2002-07-31');
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (311, 0.5, 102, 'Denver', NULL);
insert into shipment (cust_id, weight, `truck_#`, destination, ship_date) values (311, 100, 102, 'St. Louis', NULL);

# ------------------------------------
# a)
select customer.cust_name from customer, shipment where customer.cust_id=shipment.cust_id AND shipment.destination='Sioux City';
#select cust_name from customer join shipment on customer.cust_id=shipment.cust_id where destination='Sioux City'

# b)
select destination from customer join shipment on customer.cust_id=shipment.cust_id where annual_revenue < 1000000;

# c)
select distinct city_name, population from city join shipment on city.city_name=shipment.destination where weight > 100;

# d)
select distinct cust_name from customer join shipment on customer.cust_id=shipment.cust_id where (annual_revenue > 5000000 AND weight < 1);

# e)
select distinct cust_name from customer join shipment on customer.cust_id=shipment.cust_id where (annual_revenue > 5000000 AND weight < 1) OR destination='San Francisco';

# f)
select distinct driver_name from customer join shipment on customer.cust_id=shipment.cust_id join city on city.city_name=shipment.destination join truck on truck.`truck_#`=shipment.`truck_#` where annual_revenue > 20000000 AND population > 1000000;

# g)
select distinct destination from customer join shipment on customer.cust_id=shipment.cust_id where annual_revenue > 15000000;

# h)
select distinct driver_name from shipment join truck on shipment.`truck_#`=truck.`truck_#` where weight > 100;

# i)
select distinct cust_name, annual_revenue from customer join shipment on customer.cust_id=shipment.cust_id where weight > 100;

# j)
select distinct cust_name, annual_revenue from customer join shipment on customer.cust_id=shipment.cust_id join truck on truck.`truck_#`=shipment.`truck_#` where driver_name='Jensen';

# k)
select cust_name,cust_id from (select cust_id,count(distinct `truck_#`) as truck_count from shipment group by cust_id) as t natural join customer where t.truck_count = (select count(*) from truck);
 
# l)
select destination from (select destination,count(distinct cust_id) as cust_count from shipment group by destination) as t where t.cust_count = (select count(*) from customer);
 
# m)
select driver_name,`truck_#` from(select `truck_#`,count(distinct destination) as dest_count from shipment group by `truck_#`) as t natural join truck where t.dest_count = (select count(*) from city);

# n)
select distinct cust_name from customer join shipment on customer.cust_id=shipment.cust_id where destination='St. Louis' OR cust_type='Manufacturer';

# o)
select city_name from city join shipment on shipment.destination=city.city_name where population > 1000000 and weight=100 and cust_id=311;

# p)
select distinct `truck_#` from truck natural join shipment where driver_name='Jake Stinson' and destination not like 'Denver';

# q)
select cust_name from customer join shipment on customer.cust_id=shipment.cust_id join city on shipment.destination=city.city_name where annual_revenue > 10000000 and weight < 1 and population < 10000;

# r)
create view customer_under_1M as select * from customer where annual_revenue < 1000000;
create view customer_between_1M_5M as select * from customer where annual_revenue between 1000000 AND 5000000;
create view customer_over_5M as select * from customer where annual_revenue > 5000000;
select * from customer_over_5M;

# s)
select distinct driver_name from truck join shipment on truck.`truck_#`=shipment.`truck_#` join customer_over_5M on shipment.cust_id=customer_over_5M.cust_id where destination like 'Los Angeles';

select distinct city_name, population from city join shipment on city.city_name=shipment.destination join customer_between_1M_5M on shipment.cust_id=customer_between_1M_5M.cust_id;

select driver_name, population from truck join shipment on truck.`truck_#`=shipment.`truck_#` join customer_under_1M on shipment.cust_id=customer_under_1M.cust_id join city on city.city_name=shipment.destination;
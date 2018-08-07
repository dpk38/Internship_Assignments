create table contacts (c_id int primary key auto_increment, firstName varchar(20), lastName varchar(20));
create table mobile (c_id int, m_id int, countryCode varchar(5), m_number varchar(15), constraint foreign key (c_id) references contacts (c_id) on delete cascade);
create table home (c_id int, h_id int, countryCode varchar(5), areaCode varchar(5), h_number varchar(15), constraint foreign key (c_id) references contacts (c_id) on delete cascade);
create table office (c_id int, o_id int, countryCode varchar(5), areaCode varchar(5), extension varchar(5), o_number varchar(15), constraint foreign key (c_id) references contacts (c_id) on delete cascade);
create table email (c_id int, e_id int, email varchar(30), constraint foreign key (c_id) references contacts (c_id) on delete cascade);

select * from contacts;
select * from mobile;
select * from home;
select * from office;
select * from email;

truncate table home;
truncate table mobile;
truncate table office;
truncate table email;
truncate table contacts;

drop table mobile;
drop table home;
drop table office;
drop table email;
drop table contacts;

select * from contacts join mobile on contacts.c_id=mobile.c_id join home on contacts.c_id=home.c_id join office on contacts.c_id=office.c_id join email on contacts.c_id=email.c_id;
select contacts.c_id, firstName, lastName, m_number, h_number, o_number, email from contacts join mobile on contacts.c_id=mobile.c_id join home on contacts.c_id=home.c_id join office on contacts.c_id=office.c_id join email on contacts.c_id=email.c_id;
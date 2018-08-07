use assignment2;
create table Employee(e_id int primary key,dname varchar(20),e_name varchar(20),pay_day float,leave_bal float);
create table Leave_count(e_id int,sick int,personal int, vacation int,flexi int,foreign key (e_id) references Employee(e_id));
create table Holiday(h_id int primary key,h_name varchar(20),h_type varchar(20),h_date date);
create table E_log(log_id int primary key,e_id int,l_type varchar(20),l_date date,applied_date date,status varchar(20),foreign key (e_id) references Employee(e_id));
insert into Employee (e_id,d_id,e_name,pay_day) values(102,11,"Praveen",50);
insert into Department(d_id,dept_name) values(10,"QA");
insert into Holiday values(51,"New Year","Common",'2018-01-01');
insert into Holiday values(default,"Pongal","Common",'2018-01-14');
insert into Holiday values(default,"Republic Day","Common",'2018-01-26');
insert into Holiday values(default,"Martyr Day","Common",'2018-05-01');
insert into Holiday values(default,"Independance Day","Common",'2018-08-15');
insert into Holiday values(default,"Deewali","Common",'2018-10-01');
insert into Holiday values(default,"Gandhi Jayanthi","Common",'2018-10-02');
insert into Holiday values(default,"Christmas","Common",'2018-12-25');
insert into Holiday values(default,"Ugadi","Flexi",'2018-07-05');
insert into Holiday values(default,"Ramzan","Flexi",'2018-06-01');

select * from Holiday;

insert into Department(d_id,dept_name) values(11,"CRM");
insert into Department(d_id,dept_name) values(12,"DEV");
insert into Department(d_id,dept_name) values(13,"BOOKS");

insert into Employee (e_id,d_id,e_name,pay_day) values(102,11,"Praveen",50);
insert into Employee (e_id,d_id,e_name,pay_day) values(103,12,"Praveen",50);
insert into Employee (e_id,d_id,e_name,pay_day) values(104,13,"Naveen",100);
insert into Employee (e_id,d_id,e_name,pay_day) values(105,11,"Joel",75);

insert into E_log(0

update Employee set e_name = 'John' where e_id=103;

select * from Leave_count;


# 1)
select e_name from Employee where e_id = (select e_id from Leave_count order by (sick + personal + vacation + flexi) desc limit 1);

# 2)
select e_name, (22-(sick + personal + vacation + flexi)) from Employee join Leave_count on Employee.e_id=Leave_count.e_id;
#3
SELECT e_name from Employee join Leave_count on Employee.e_id=Leave_count.e_id where( sick+personal+flexi+vacation)=22;

# 4)d_id
select dept_name, sum(sick+personal+vacation+flexi) from Employee join Leave_count on Employee.e_id=Leave_count.e_id join Department on Department.d_id=Employee.d_id group by dept_name order by sum(sick + personal + vacation + flexi) desc;
 
# 5)
select e_name from Employee join E_log on Employee.e_id=E_log.e_id where l_date between '2018-12-01' AND '2018-12-31';

 #6) 
 select Month(l_date), count(*) from Employee join E_log on Employee.e_id=E_log.e_id where d_id=11 group by Month(l_date) order by count(*) desc; 
delimiter //
create trigger employee_trigger after insert on Employee
for each row
begin

insert into Leave_count (e_id, sick, personal, vacation, flexi) values (New.e_id,5,5,10,2);
end; //
delimiter ;
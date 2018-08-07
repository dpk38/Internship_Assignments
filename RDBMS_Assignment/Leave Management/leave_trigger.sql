delimiter //
create trigger log_trigger after insert on E_log
for each row
begin
Declare date date1;

date1=New.l_date;
if(select * from Holiday where h_date!=date1){

}

insert into Leave_count (e_id, sick, personal, vacation, flexi) values (New.e_id,5,5,10,2);
end; //
delimiter ;
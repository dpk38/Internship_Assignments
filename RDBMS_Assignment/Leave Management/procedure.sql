CREATE PROCEDURE `encashment_proecedure` ()
BEGIN

select (sick + personal + vacation + flexi) * pay_day from Leave_count join Employee on Leave_count.e_id=Employee.e_id;

END

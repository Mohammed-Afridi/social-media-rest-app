insert into user_details(id,birth_date,name)
values (10001,current_date(),'Goli');
-- When we save this , we will get an error saying user_details not found
-- Solution : add property defer
insert into user_details(id,birth_date,name)
values (10002,current_date(),'Baje');
insert into user_details(id,birth_date,name)
values (10003,current_date(),'Ligo');

insert into post(id,description,user_id)
values (20001,'Hi guys',10001);
insert into post(id,description,user_id)
values (20002,'I am Goli',10001);
insert into post(id,description,user_id)
values (20003,'Hello guys',10002);
insert into post(id,description,user_id)
values (20004,'I am baje',10002);
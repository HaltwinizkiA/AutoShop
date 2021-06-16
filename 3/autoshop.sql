drop database AutoShop;
create database if not exists AutoShop;
use AutoShop;
CREATE TABLE `AutoShop`.`Orders`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
owners_name nvarchar(30)not null,
create_date nvarchar(30)NOT NULL,
planned_start_date date not null,
completion_date date ,
status nvarchar(30)not null,
car_id int(30) not null,
master_id int(30)
);
CREATE TABLE `AutoShop`.`Masters`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
name nvarchar(30)not null,
date_birth date not null,
specialty nvarchar(30) not null,
phone_number nvarchar(30) not null,
status bit not null
);
CREATE TABLE `AutoShop`.`Works`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
name nvarchar(30)NOT NULL,
price float(30)NOT NULL
);
CREATE TABLE `AutoShop`.`Boxboxs`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
capacity int (30)not null
);
create table `AutoShop`.`Cars`(
id int(30)not null AUTO_INCREMENT primary key,
mark nvarchar(30)NOT NULL,
model nvarchar(30)NOT NULL,
color nvarchar(30)NOT NULL,
number nvarchar(30)NOT NULL
);
create table `AutoShop`.`orders_works`(
order_id int(30),
work_id int(30)
);
create table `AutoShop`.`box_cars`(
box_id int(30)not null,
car_id int(30)not null
);
insert into `AutoShop`.`cars`(mark,model,color,number) values('ford','escort','boss-green','KH-2425');
insert into `AutoShop`.`cars`(mark,model,color,number) values('BMW','m5','black','KM-8888');
insert into `AutoShop`.`cars`(mark,model,color,number) values('Volkswagen','golf','silver','NI-1233');
insert into `AutoShop`.`cars`(mark,model,color,number) values('Toyota','camry','white','OP-3555');

insert into `AutoShop`.`Boxs`(capacity) values(5);

insert into `AutoShop`.`box_cars`(box_id,car_id)values(1,1);
insert into `AutoShop`.`box_cars`(box_id,car_id)values(1,2);
insert into `AutoShop`.`box_cars`(box_id,car_id)values(1,3);
insert into `AutoShop`.`box_cars`(box_id,car_id)values(1,4);

insert into `AutoShop`.`works`(name,price)values('brew',50.5);
insert into `AutoShop`.`works`(name,price)values('tire fitting',20.5);
insert into `AutoShop`.`works`(name,price)values('paint',100.5);
insert into `AutoShop`.`works`(name,price)values('engine repair',200);
insert into `AutoShop`.`works`(name,price)values('wash',13.6);

insert into `AutoShop`.`masters`(name,date_birth,specialty,phone_number,status )values('lesha','1997-08-14','programmer',+353445552222,1);
insert into `AutoShop`.`masters`(name,date_birth,specialty,phone_number,status )values('Maxim','1997-04-19','electric',+35341232454,0);
insert into `AutoShop`.`masters`(name,date_birth,specialty,phone_number,status )values('Oleg','1994-01-15','Mehaic',+35304123,0);

insert into `AutoShop`.`Orders`(owners_name,create_date,planned_start_date,completion_date,status,car_id,master_id)values('sasha','2021-06-15','2021-06-15',null,'ACCEPTED',1,1);
               
insert into `AutoShop`.`orders_works`(order_id,work_id)values(1,2);
insert into `AutoShop`.`orders_works`(order_id,work_id)values(1,4);
insert into `AutoShop`.`orders_works`(order_id,work_id)values(1,5);

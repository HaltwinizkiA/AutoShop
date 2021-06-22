CREATE TABLE `autoshop`.`Orders`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
owners_name nvarchar(30)not null,
create_date nvarchar(30)NOT NULL,
planned_start_date date not null,
completion_date date default null ,
status nvarchar(30)not null default'ACCEPTED',
car_id int(30) not null,
master_id int(30) default null
);
CREATE TABLE `autoshop`.`Masters`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
name nvarchar(30)not null,
date_birth date not null,
specialty nvarchar(30) not null,
phone_number nvarchar(30) not null,
status bit not null
);
CREATE TABLE `autoshop`.`Works`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
name nvarchar(30)NOT NULL,
price float(30)NOT NULL
);
CREATE TABLE `autoshop`.`Boxboxs`(
id int(30)NOT NULL AUTO_INCREMENT primary key,
capacity int (30)not null
);
create table `autoshop`.`Cars`(
id int(30)not null AUTO_INCREMENT primary key,
mark nvarchar(30)NOT NULL,
model nvarchar(30)NOT NULL,
color nvarchar(30)NOT NULL,
number nvarchar(30)NOT NULL
);
create table `autoshop`.`orders_works`(
order_id int(30),
work_id int(30)
);
create table `autoshop`.`box_cars`(
box_id int(30)not null,
car_id int(30)not null primary key
);

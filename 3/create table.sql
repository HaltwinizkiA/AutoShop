CREATE TABLE `autoshop`.`Orders`(
id int(30)NOT NULL AUTO_INCREMENT ,
owners_name nvarchar(30)not null,
create_date nvarchar(30)NOT NULL,
planned_start_date date not null,
completion_date date default null ,
status nvarchar(30)not null default'ACCEPTED',
car_id int(30) not null,
master_id int(30) default null,
CONSTRAINT PK_ORDERS_ID PRIMARY KEY (id)
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
id int(30)NOT NULL AUTO_INCREMENT ,
name nvarchar(30)NOT NULL,
price float(30)NOT NULL,
CONSTRAINT PK_WORKS_ID PRIMARY KEY (id)
);

create table `autoshop`.`orders_works`(
 order_id integer not null,
  work_id  integer not null,
  CONSTRAINT FK_ORDERS_ID FOREIGN KEY (order_id)
      REFERENCES orders (id),
  CONSTRAINT FK_WORKS_ID FOREIGN KEY (work_id)
      REFERENCES works (id)
);

CREATE TABLE `autoshop`.`Box`(
id int(30)NOT NULL AUTO_INCREMENT,
capacity int (30)not null,
 CONSTRAINT PK_BOX_ID PRIMARY KEY (id)
);

create table `autoshop`.`Cars`(
id int(30)not null AUTO_INCREMENT,
mark nvarchar(30)NOT NULL,
model nvarchar(30)NOT NULL,
color nvarchar(30)NOT NULL,
number nvarchar(30)NOT NULL,
 CONSTRAINT PK_CARS_ID PRIMARY KEY (id)
 
);

create table `autoshop`.`box_cars`(
box_id int(30)not null,
car_id int(30)not null primary key,
 CONSTRAINT FK_BOX_ID FOREIGN KEY (box_id)
      REFERENCES box (id),
  CONSTRAINT FK_CARS_ID FOREIGN KEY (car_id)
      REFERENCES cars (id)
);

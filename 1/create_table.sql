
CREATE TABLE `task10bd`.`product`(
maker nvarchar(30)NOT NULL,
model nvarchar(30)NOT NULL PRIMARY KEY,
type nvarchar(30)NOT NULL
);
CREATE TABLE `task10bd`.`pc`(
code int(30)NOT NULL AUTO_INCREMENT,
model nvarchar(30)NOT NULL,
speed float(30)NOT NULL,
ram int(30)NOT NULL,
hd int(30)NOT NULL,
cd nvarchar(30)NOT NULL,
price int(30)NOT NULL,
PRIMARY KEY(`code`),INDEX(`model`,`code`)
);
CREATE TABLE `task10bd`.`laptop`(
code int(30)NOT NULL AUTO_INCREMENT,
model nvarchar(30)NOT NULL,
speed float(30)NOT NULL,
ram int(30)NOT NULL,
hd int(30)NOT NULL,
screen float(30)NOT NULL,
price int(30)NOT NULL,
PRIMARY KEY(`code`),INDEX(`model`,`code`)
);
CREATE TABLE `task10bd`.`printer`(
code int(30)NOT NULL AUTO_INCREMENT,
model nvarchar(30)NOT NULL,
color bit NOT NULL,
type nvarchar(30)NOT NULL,
price int(30)NOT NULL,
PRIMARY KEY(`code`),INDEX(`model`,`code`)

);
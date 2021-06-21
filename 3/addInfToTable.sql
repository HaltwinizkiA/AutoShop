insert into `autoshop`.`cars`(mark,model,color,number) values('ford','escort','boss-green','KH-2425');
insert into `autoshop`.`cars`(mark,model,color,number) values('BMW','m5','black','KM-8888');
insert into `autoshop`.`cars`(mark,model,color,number) values('Volkswagen','golf','silver','NI-1233');
insert into `autoshop`.`cars`(mark,model,color,number) values('Toyota','camry','white','OP-3555');

insert into `autoshop`.`Boxs`(capacity) values(5);

insert into `autoshop`.`box_cars`(box_id,car_id)values(1,1);
insert into `autoshop`.`box_cars`(box_id,car_id)values(1,2);
insert into `autoshop`.`box_cars`(box_id,car_id)values(1,3);
insert into `autoshop`.`box_cars`(box_id,car_id)values(1,4);

insert into `autoshop`.`works`(name,price)values('brew',50.5);
insert into `autoshop`.`works`(name,price)values('tire fitting',20.5);
insert into `autoshop`.`works`(name,price)values('paint',100.5);
insert into `autoshop`.`works`(name,price)values('engine repair',200);
insert into `autoshop`.`works`(name,price)values('wash',13.6);

insert into `autoshop`.`masters`(name,date_birth,specialty,phone_number,status )values('lesha','1997-08-14','programmer',+353445552222,1);
insert into `autoshop`.`masters`(name,date_birth,specialty,phone_number,status )values('Maxim','1997-04-19','electric',+35341232454,0);
insert into `autoshop`.`masters`(name,date_birth,specialty,phone_number,status )values('Oleg','1994-01-15','Mehaic',+35304123,0);

insert into `autoshop`.`Orders`(owners_name,create_date,planned_start_date,completion_date,car_id,master_id)values('sasha','2021-06-15','2021-06-15',null,1,1);
               
insert into `autoshop`.`orders_works`(order_id,work_id)values(1,2);
insert into `autoshop`.`orders_works`(order_id,work_id)values(1,4);
insert into `autoshop`.`orders_works`(order_id,work_id)values(1,5);

package ui.action.order;


import facade.AutoShopAdministrator;

import model.entity.car.Car;
import ui.api.IAction;

import ui.utils.TextWorker;
import utils.FileWorker;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddOrder implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        TextWorker textWorker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllOrder();
        textWorker.println("enter car:\nmark-");
        String mark = textWorker.getStringInput();
        textWorker.println("model");
        String model = textWorker.getStringInput();
        textWorker.println("color");
        String color = textWorker.getStringInput();
        textWorker.println("number");
        textWorker.println("id");
        Integer carId=textWorker.getIntInput();
        String number = textWorker.getStringInput();
        textWorker.println("owner name:");
        String owner = textWorker.getStringInput();
        textWorker.println("planned start work date in format - HH:mm dd/MM/yy ");
        String date = textWorker.getStringLine();
        Car car = new Car(mark, model, color, number,carId);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date plannedStartDate = null;
        try {
            plannedStartDate = dateFormat.parse(date);

        } catch (ParseException e) {
            worker.logger("add order exception" + e);
        }
        textWorker.println("enter id ");
        Integer id=textWorker.getIntInput();

        AutoShopAdministrator.getInstance().addOrder(plannedStartDate,car,owner,id);
        textWorker.println("order created");



    }
}

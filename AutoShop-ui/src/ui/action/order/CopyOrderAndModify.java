package ui.action.order;


import facade.AutoShopAdministrator;
import model.entity.car.Car;
import model.entity.work.Work;
import model.enums.OrderStatus;
import ui.api.IAction;

import ui.utils.TextWorker;
import utils.FileWorker;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CopyOrderAndModify implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        TextWorker textWorker = new TextWorker();
        Car car = null;
        String ownersName = null;
        List<Work> workList = null;
        String masterName = null;
        Integer boxNum = null;
        OrderStatus status = null;
        Date plannedStartDate = null;
        Double price = null;
        AutoShopAdministrator.getInstance().viewAllOrder();
        textWorker.println("enter order num");
        int orderNum = textWorker.getIntInput();

        while (true) {
            textWorker.println("enter field number to modify \n1) car\n2) owner's name\n3) work list\n4) master\n5) status\n6) planned start date /");
            switch (textWorker.getIntInput()) {
                case 1:
                    textWorker.println("enter mark");
                    String mark = textWorker.getStringInput();
                    textWorker.println("enter model");
                    String model = textWorker.getStringInput();
                    textWorker.println("enter color");
                    String color = textWorker.getStringInput();
                    textWorker.println("enter number");
                    String number = textWorker.getStringInput();
                    textWorker.println("enter id ");
                    Integer carId=textWorker.getIntInput();
                    car = new Car(mark, model, color, number,carId);
                    break;
                case 2:
                    textWorker.println("enter owner's name ");
                    ownersName = textWorker.getStringInput();
                    break;
                case 3:
                    if (workList == null) {
                        workList = new ArrayList<>();
                    }
                    price = 0.0;
                    while (true) {
                        AutoShopAdministrator.getInstance().viewWorkList();
                        textWorker.println("enter work number 69 for stop");
                        int workNum = textWorker.getIntInput();
                        if (workNum == 69) {
                            break;
                        }
                        workList.add(AutoShopAdministrator.getInstance().getWorkList().get(workNum));
                        price += AutoShopAdministrator.getInstance().getWorkList().get(workNum).getPrice();

                    }
                    break;
                case 4:
                    textWorker.println("enter master name");
                    masterName = textWorker.getStringInput();
                    break;
                case 5:
                    textWorker.println("select status:");
                    OrderStatus[] orderStatuses = OrderStatus.values();
                    for (int i = 0; i < orderStatuses.length; i++) {
                        textWorker.println(i + " " + orderStatuses[i].toString());
                    }
                    status = orderStatuses[textWorker.getIntInput()];

                    break;
                case 6:
                    textWorker.println("planned start work date in format - HH:mm dd/MM/yy ");
                    String date=textWorker.getStringLine();
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
                    dateFormat.setLenient(false);
                    try {
                        plannedStartDate = dateFormat.parse(date);

                    } catch (ParseException e) {
                        worker.logger("add order exception" + e);
                    }

            }
            break;
        }
        textWorker.println("enter id ");
        Integer id=textWorker.getIntInput();

        AutoShopAdministrator.getInstance().copyOrderAndModify(orderNum,car,ownersName,workList,masterName,status,plannedStartDate,price,id);

    }
}

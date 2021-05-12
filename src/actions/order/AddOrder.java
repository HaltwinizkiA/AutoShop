package actions.order;

import api.IAction;
import model.car.Car;
import model.order.Order;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AddOrder implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter car:\nmark-");
        String mark = scanner.nextLine();
        System.out.println("model");
        String model = scanner.nextLine();
        System.out.println("color");
        String color = scanner.nextLine();
        System.out.println("number");
        String number = scanner.nextLine();
        System.out.println("owner name:");
        String owner = scanner.nextLine();
        System.out.println("work:");
        String work = scanner.nextLine();

        System.out.println("planned start work date in format - HH:mm dd/MM/yy ");
        String date = scanner.nextLine();
        System.out.println("price");
        double price = Double.parseDouble(scanner.nextLine());
        Car car = new Car(mark, model, color, number);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date plannedStartDate = null;
        try {
            plannedStartDate = dateFormat.parse(date);

        } catch (ParseException e) {
            worker.logger("add order exception" + e);
        }

        Order order = new Order(new Date(), plannedStartDate, car, owner, work, price);

        List<Order> orderList = (List<Order>) worker.reader(path);
        orderList.add(order);
        worker.writer(orderList, path);


    }
}

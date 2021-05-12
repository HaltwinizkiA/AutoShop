package actions.order;

import api.IAction;
import model.car.Car;
import model.order.Order;
import model.work.Work;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddOrder implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String workListPath = properties.getProperty("workList");
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
        List<Work> workList = (List<Work>) worker.reader(workListPath);
        List<Work> workInOrder = new ArrayList<>();
        while (true) {
            for (int i = 0; i < workList.size(); i++) {
                System.out.println(i + " " + workList.get(i));
            }
            System.out.println("select work");
            try {


                int select = scanner.nextInt();
                workInOrder.add(workList.get(select));
                workList.remove(select);
            } catch (Exception e) {
                worker.logger(e.toString());
                break;
            }


        }


        System.out.println("planned start work date in format - HH:mm dd/MM/yy ");
        String date = scanner.nextLine();
        date=scanner.nextLine();
        Car car = new Car(mark, model, color, number);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date plannedStartDate = null;
        try {
            plannedStartDate = dateFormat.parse(date);

        } catch (ParseException e) {
            worker.logger("add order exception" + e);
        }
        double price = 0;
        for (Work work : workInOrder) {
            price += work.getPrice();
        }

        Order order = new Order(new Date(), plannedStartDate, car, owner, workInOrder, price);

        List<Order> orderList = (List<Order>) worker.reader(path);
        orderList.add(order);
        worker.writer(orderList, path);
        System.out.println("order created");


    }
}

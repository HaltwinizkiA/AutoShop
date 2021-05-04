package actions.order;

import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ModifyPlannedStartDate implements IAction {
    private final FileWorker worker = new FileWorker();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {

        List<Order> orders = worker.ordersRead();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).toString());
        }
        System.out.println("select order number");
        int orderNumber=scanner.nextInt();
        System.out.println("planned start work date in format - HH:mm dd/MM/yy " );
        String date = scanner.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date plannedStartDate=null;
        try {  plannedStartDate = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        orders.get(orderNumber).setPlannedStartDate(plannedStartDate);
        worker.ordersWriter(orders);

    }
}

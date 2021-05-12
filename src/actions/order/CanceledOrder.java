package actions.order;

import annotation.OrderStatus;
import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class CanceledOrder implements IAction {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(path);
        System.out.println("enter the order number for close");
        int num = scanner.nextInt();
        orders.get(num).setStatus(OrderStatus.CANCELED);
        orders.get(num).setDateOfCompletion(new Date());
        worker.writer(orders, path);
    }
}

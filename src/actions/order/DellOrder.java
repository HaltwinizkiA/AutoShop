package actions.order;

import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DellOrder implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(path);
        System.out.println("enter the order number for remove");
        int num = scanner.nextInt();
        orders.remove(num);
        worker.writer(orders, path);


    }
}

package actions.order;

import api.IAction;
import comporators.order.OrderDateCreateSort;
import model.order.Order;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SortOrderDateCreate implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(path);
        Collections.sort(orders, new OrderDateCreateSort());
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

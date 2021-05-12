package actions.order;

import api.IAction;
import comporators.order.OrderDateCompliteSort;
import model.order.Order;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SortOrderDateComplete implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(path);
        Collections.sort(orders, new OrderDateCompliteSort());
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}

package actions.order;

import annotation.OrderStatus;
import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;

public class ViewOrderInProgress implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orderList = (List<Order>) worker.reader(path);
        for (Order order : orderList) {
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println(order);
            }
        }
    }
}

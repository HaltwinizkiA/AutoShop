package actions.order;

import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;

public class ViewAllOrders implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(path);

        for (int i=0;i<orders.size();i++) {
            System.out.println(i+" "+orders.get(i).toString());
        }
    }
}

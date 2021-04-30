package actions.order;

import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.List;

public class ViewAllOrders implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        List<Order> orders = worker.ordersRead();
        for (int i=0;i<orders.size();i++) {
            System.out.println(orders.get(i).toString());
        }
    }
}

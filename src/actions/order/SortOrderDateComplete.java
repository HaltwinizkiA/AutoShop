package actions.order;

import api.IAction;
import comporators.order.OrderDateCompliteSort;
import comporators.order.OrderDateCreateSort;
import model.order.Order;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;

public class SortOrderDateComplete implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Order> orders=worker.ordersRead();
        Collections.sort(orders,new OrderDateCompliteSort());
        for (Order order:orders){
            System.out.println(order);
        }
    }
}

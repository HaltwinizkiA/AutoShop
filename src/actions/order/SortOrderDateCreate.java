package actions.order;

import api.IAction;
import comporators.order.OrderDateCreateSort;
import model.order.Order;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;

public class SortOrderDateCreate implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Order> orders=worker.ordersRead();
        Collections.sort(orders,new OrderDateCreateSort());
        for (Order order:orders){
            System.out.println(order);
        }
    }
}

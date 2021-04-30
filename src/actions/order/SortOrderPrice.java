package actions.order;

import api.IAction;
import comporators.order.OrderDateCompliteSort;
import comporators.order.OrderPriceSort;
import model.order.Order;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;

public class SortOrderPrice implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Order> orders=worker.ordersRead();
        Collections.sort(orders,new OrderPriceSort());
        for (Order order:orders){
            System.out.println(order);
        }
    }
}

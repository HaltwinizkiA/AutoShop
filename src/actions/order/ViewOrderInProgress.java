package actions.order;

import annotation.OrderStatus;
import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.List;

public class ViewOrderInProgress implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Order> orderList=worker.ordersRead();
        for (Order order:orderList){
            if (order.getStatus()== OrderStatus.IN_PROGRESS){
                System.out.println(order);
            }
        }
    }
}

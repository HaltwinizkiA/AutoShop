package actions.order;

import annotation.OrderStatus;
import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.util.List;
import java.util.Scanner;

public class CloseOrder implements IAction {
    @Override
    public void execute() {
        Scanner scanner=new Scanner(System.in);
        FileWorker worker=new FileWorker();
        List<Order> orders=worker.ordersRead();
        System.out.println("enter the order number for close");
        int num=scanner.nextInt();
        orders.get(num).setStatus(OrderStatus.CLOSED);
        worker.ordersWriter(orders);
    }
}

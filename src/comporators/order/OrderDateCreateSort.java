package comporators.order;

import model.order.Order;

import java.util.Comparator;

public class OrderDateCreateSort implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        return (int)(o1.getCreateOrderDate().getTime()-o2.getCreateOrderDate().getTime());
    }
}

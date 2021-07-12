package autoShop.model.comporators.order;





import autoShop.model.entity.order.Order;

import java.util.Comparator;

public class OrderComparator {
    private Comparator<Order> orderDateComparator=new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int)(o1.getDateOfCompletion().getTime()-o2.getDateOfCompletion().getTime());
        }
    };
    private Comparator<Order> orderDateCompliteComparator=new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int)(o1.getCreateOrderDate().getTime()-o2.getCreateOrderDate().getTime());
        }
    };
    private Comparator<Order> orderPlannedStartDate=new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int) (o1.getPlannedStartDate().getTime()-o2.getPlannedStartDate().getTime());
        }
    };
    private Comparator<Order> orderPriceComparator=new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return (int)(o1.getPrice()-o2.getPrice());
        }
    };

    public Comparator<Order> getOrderDateComparator() {
        return orderDateComparator;
    }

    public Comparator<Order> getOrderDateCompliteComparator() {
        return orderDateCompliteComparator;
    }

    public Comparator<Order> getOrderPlannedStartDate() {
        return orderPlannedStartDate;
    }

    public Comparator<Order> getOrderPriceComparator() {
        return orderPriceComparator;
    }
}

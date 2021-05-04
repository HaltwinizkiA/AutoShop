package actions.order;

import api.IAction;
import model.master.Master;
import model.order.Order;
import utils.FileWorker;

import java.util.List;

public class TransferTheOrderToTheMaster implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Master> masters=worker.mastersReader();
        List<Order> orders=worker.ordersRead();

    }
}

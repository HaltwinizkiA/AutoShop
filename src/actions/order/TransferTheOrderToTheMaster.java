package actions.order;

import api.IAction;
import model.master.Master;
import model.order.Order;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class TransferTheOrderToTheMaster implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();

        String pathMaster = properties.getProperty("masterList");
        List<Master> masters = (List<Master>) worker.reader(pathMaster);
        String pathOrder = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(pathOrder);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < masters.size(); i++) {
            System.out.println(masters.get(i).toString());
        }
        System.out.println("Select master num");
        int masterNum = scanner.nextInt();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).toString());
        }
        System.out.println("Select order num");
        int orderNum = scanner.nextInt();
        masters.get(masterNum).setOrder(orders.get(orderNum));
        worker.writer(masters, pathMaster);
        worker.writer(orders, pathOrder);


    }
}

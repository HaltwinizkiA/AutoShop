package actions.master;

import api.IAction;
import model.master.Master;
import model.order.Order;
import utils.FileWorker;

import java.util.List;
import java.util.Scanner;

public class ViewMasterInOrder implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Master> masters=worker.mastersReader();
        List<Order> orders=worker.ordersRead();

        for (int i=0;i<orders.size();i++){
            System.out.println(i+" "+orders.get(i));
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("select order num:");
        int num=scanner.nextInt();
        for (Master master:masters){
            if (master.getOrder().equals(orders.get(num))){
                System.out.println(master);
            }
        }

    }
}

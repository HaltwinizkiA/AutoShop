package actions.order;

import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ViewOrderInTime implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Order> orderList=worker.ordersRead();
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter time interval in format HH:mm dd/MM/yy \nfirst:");
        String time1=scanner.nextLine();
        System.out.println("second");
        String time2=scanner.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date firstTime=null;
        Date secondTime=null;
        try {  firstTime = dateFormat.parse(time1);
                secondTime=dateFormat.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0;i<orderList.size();i++){
            if (orderList.get(i).getCreateOrderDate().getTime()>=firstTime.getTime()&orderList.get(i).getCreateOrderDate().getTime()<=secondTime.getTime()){
                System.out.println("# "+i+" "+orderList.get(i));
            }
        }

    }
}

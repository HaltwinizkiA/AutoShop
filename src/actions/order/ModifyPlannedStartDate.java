package actions.order;

import api.IAction;
import model.order.Order;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ModifyPlannedStartDate implements IAction {


    @Override
    public void execute() {
         FileWorker worker = new FileWorker();
         Scanner scanner = new Scanner(System.in);
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
        List<Order> orders = (List<Order>) worker.reader(path);
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).toString());
        }
        System.out.println("select order number");
        int orderNumber=scanner.nextInt();
        System.out.println("planned start work date in format - HH:mm dd/MM/yy " );
        String date = scanner.nextLine();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date plannedStartDate=null;
        try {  plannedStartDate = dateFormat.parse(date);

        } catch (ParseException e) {
            worker.logger("modify order exception"+e);
        }
        orders.get(orderNumber).setPlannedStartDate(plannedStartDate);
        worker.writer(orders,path);

    }
}

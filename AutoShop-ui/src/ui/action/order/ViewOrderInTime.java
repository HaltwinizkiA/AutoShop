package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import ui.utils.TextWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewOrderInTime implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        System.out.println("enter time interval in format HH:mm dd/MM/yy \nfirst:");
        String time1 = textWorker.getStringLine();
        System.out.println("second in format - HH:mm dd/MM/yy  ");
        String time2 = textWorker.getStringLine();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date firstTime = null;
        Date secondTime = null;
        try {
            firstTime = dateFormat.parse(time1);
            secondTime = dateFormat.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        AutoShopAdministrator.getInstance().viewOrderInTime(firstTime,secondTime);


    }
}

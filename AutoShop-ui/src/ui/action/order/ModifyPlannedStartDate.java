package ui.action.order;


import facade.AutoShopAdministrator;

import ui.api.IAction;
import ui.utils.TextWorker;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ModifyPlannedStartDate implements IAction {


    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        TextWorker textWorker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllOrder();
        textWorker.println("enter order num");
        int num=textWorker.getIntInput();
        System.out.println("planned start work date in format - HH:mm dd/MM/yy " );
        String date = textWorker.getStringLine();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date plannedStartDate=null;
        try {  plannedStartDate = dateFormat.parse(date);

        } catch (ParseException e) {
            worker.logger("modify order exception"+e);
        }
        AutoShopAdministrator.getInstance().modifyPlannedStartDate(num,plannedStartDate);

    }
}

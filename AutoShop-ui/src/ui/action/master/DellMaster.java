package ui.action.master;


import facade.AutoShopAdministrator;

import ui.api.IAction;
import utils.TextWorker;


public class DellMaster implements IAction {

    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllMaster();
        worker.println("enter master num");
        int num=worker.getIntInput();

        AutoShopAdministrator.getInstance().dellMaster(num);


    }
}

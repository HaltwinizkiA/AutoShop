package ui.action.master;


import facade.AutoShopAdministrator;

import ui.api.IAction;
import utils.TextWorker;


public class ViewMasterInOrder implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllOrder();
        worker.println("enter order num");
        int num=worker.getIntInput();
        AutoShopAdministrator.getInstance().viewMasterInOrder(num);

    }
}

package ui.action.master;


import facade.AutoShopAdministrator;

import ui.api.IAction;
import utils.TextWorker;


public class ViewAllMasters implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().masterBusySort();
        AutoShopAdministrator.getInstance().viewAllMaster();

    }
}

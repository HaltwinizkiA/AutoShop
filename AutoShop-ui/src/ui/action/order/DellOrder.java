package ui.action.order;


import facade.AutoShopAdministrator;

import ui.api.IAction;
import ui.utils.TextWorker;
import utils.FileWorker;


public class DellOrder implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        TextWorker textWorker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllOrder();
        textWorker.println("enter order num");
        int num=textWorker.getIntInput();
        AutoShopAdministrator.getInstance().dellOrder(num);


    }
}

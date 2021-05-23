package ui.action.box;


import facade.AutoShopAdministrator;

import ui.api.IAction;
import utils.TextWorker;


public class ModifyBoxCapacity implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        AutoShopAdministrator.getInstance().viewAllCarInBox();
        textWorker.println("enter box num");
        int boxNum=textWorker.getIntInput();
        textWorker.println("enter new capacity");
        int newCapacity=textWorker.getIntInput();
        AutoShopAdministrator.getInstance().modifyBoxCapacity(boxNum,newCapacity);

    }
}

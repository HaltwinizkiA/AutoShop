package ui.action.box;

import ui.api.IAction;
import facade.AutoShopAdministrator;

import utils.TextWorker;


public class FreeUpSpaceBox implements IAction {
    @Override
    public void execute() {

        TextWorker textWorker = new TextWorker();
        AutoShopAdministrator.getInstance().viewAllCarInBox();
        textWorker.println("enter box num");
        int boxNum = textWorker.getIntInput();
        textWorker.println("enter car num");
        int carNum = textWorker.getIntInput();
        AutoShopAdministrator.getInstance().freeUpSpaceBox(boxNum, carNum);

    }
}

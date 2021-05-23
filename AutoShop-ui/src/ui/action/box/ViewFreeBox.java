package ui.action.box;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import utils.TextWorker;


public class ViewFreeBox implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        textWorker.println("enter box num");
        int boxNum=textWorker.getIntInput();
        AutoShopAdministrator.getInstance().viewFreeBox(boxNum);

    }
}

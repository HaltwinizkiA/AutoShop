package ui.action.box;

import facade.AutoShopAdministrator;
import ui.api.IAction;
import utils.TextWorker;

public class TranferCarInBOx implements IAction {

    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        textWorker.println("enter box num");
        int boxNum=textWorker.getIntInput();
        textWorker.println("enter order num");
        int orderNum=textWorker.getIntInput();
        AutoShopAdministrator.getInstance().transferCarInBox(boxNum,orderNum);
    }
}

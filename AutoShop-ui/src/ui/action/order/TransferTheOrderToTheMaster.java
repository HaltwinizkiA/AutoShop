package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import ui.utils.TextWorker;

public class TransferTheOrderToTheMaster implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllOrder();
        textWorker.println("enter order num");
        int orderNum=textWorker.getIntInput();
        AutoShopAdministrator.getInstance().viewAllMaster();
        textWorker.println("enter master num");
        int masterNum=textWorker.getIntInput();
        AutoShopAdministrator.getInstance().orderTransferToTheMaster(orderNum,masterNum);




    }
}

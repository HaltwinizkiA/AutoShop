package action.order;


import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class TransferTheOrderToTheMaster implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter order num");
        String orderNum=textWorker.getStringInput();
        Connect.getInstance().send("viewAllMaster");
        textWorker.println("enter master num");
        String masterNum=textWorker.getStringInput();
        Connect.getInstance().send("orderTransferToTheMaster",orderNum,masterNum);





    }
}
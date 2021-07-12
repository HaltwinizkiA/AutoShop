package action.order;

import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class ViewAllOrders implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("viewAllOrder");
    }
}

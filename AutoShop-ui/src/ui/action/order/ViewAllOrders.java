package ui.action.order;


import ui.api.IAction;
import ui.connect.Connect;

public class ViewAllOrders implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("viewAllOrder");
    }
}

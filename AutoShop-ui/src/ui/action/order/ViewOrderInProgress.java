package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;

public class ViewOrderInProgress implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("viewOrderInProgress");

    }
}

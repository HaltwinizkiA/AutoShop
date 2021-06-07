package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;

public class SortOrderDateCreate implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortDateCreate");
        Connect.getInstance().send("viewAllOrder");

    }
}

package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;


public class SortOrderDateComplete implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortDateComplete");
        Connect.getInstance().send("viewAllOrder");

    }
}

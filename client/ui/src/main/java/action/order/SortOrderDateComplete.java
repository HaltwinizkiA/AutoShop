package action.order;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class SortOrderDateComplete implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortDateComplete");
        Connect.getInstance().send("viewAllOrder");

    }
}

package action.order;



import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class SortOrderDateCreate implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortDateCreate");
        Connect.getInstance().send("viewAllOrder");

    }
}

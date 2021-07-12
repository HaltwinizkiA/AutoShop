package action.order;


import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class SortOrderPrice implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortPrice");
        Connect.getInstance().send("viewAllOrder");
        }
    }


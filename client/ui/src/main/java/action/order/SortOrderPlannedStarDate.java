package action.order;


import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class SortOrderPlannedStarDate implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortPlannedStartDate");
        Connect.getInstance().send("viewAllOrder");

        }

    }


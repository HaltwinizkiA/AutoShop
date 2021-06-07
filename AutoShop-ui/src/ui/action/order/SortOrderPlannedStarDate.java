package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;

public class SortOrderPlannedStarDate implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortPlannedStartDate");
        Connect.getInstance().send("viewAllOrder");

        }

    }


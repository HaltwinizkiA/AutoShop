package ui.action.order;


import ui.api.IAction;
import ui.connect.Connect;

public class SortOrderPrice implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("ordersSortPrice");
        Connect.getInstance().send("viewAllOrder");
        }
    }


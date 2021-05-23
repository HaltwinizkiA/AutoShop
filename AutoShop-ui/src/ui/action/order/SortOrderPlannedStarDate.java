package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;

public class SortOrderPlannedStarDate implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().ordersSortPlannedStartDate();
        AutoShopAdministrator.getInstance().viewAllOrder();
        }

    }


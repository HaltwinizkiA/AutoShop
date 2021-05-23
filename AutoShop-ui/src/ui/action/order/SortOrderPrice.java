package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;

public class SortOrderPrice implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().ordersSortPrice();
        AutoShopAdministrator.getInstance().viewAllOrder();
        }
    }


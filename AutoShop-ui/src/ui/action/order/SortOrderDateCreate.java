package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;

public class SortOrderDateCreate implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().ordersSortDateCreate();
        AutoShopAdministrator.getInstance().viewAllOrder();
    }
}

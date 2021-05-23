package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import utils.FileWorker;
import utils.TextWorker;

public class SortOrderDateComplete implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().ordersSortDateComplete();
        AutoShopAdministrator.getInstance().viewAllOrder();

    }
}

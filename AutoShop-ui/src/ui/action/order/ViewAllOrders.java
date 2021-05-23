package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;

public class ViewAllOrders implements IAction {
    @Override
    public void execute() {

        AutoShopAdministrator.getInstance().viewAllOrder();
    }
}

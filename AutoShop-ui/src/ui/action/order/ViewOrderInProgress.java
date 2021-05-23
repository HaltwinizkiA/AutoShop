package ui.action.order;


import facade.AutoShopAdministrator;
import ui.api.IAction;

public class ViewOrderInProgress implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().viewOrderInProgress();
    }
}

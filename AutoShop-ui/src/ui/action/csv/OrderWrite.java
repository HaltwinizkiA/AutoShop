package ui.action.csv;

import facade.AutoShopAdministrator;
import ui.api.IAction;

public class OrderWrite implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().csvOrderListWrite();
    }
}

package ui.action.csv;

import facade.AutoShopAdministrator;
import ui.api.IAction;

public class MasterRead implements IAction {
    @Override
    public void execute() {
        AutoShopAdministrator.getInstance().csvMasterListRead();
    }
}

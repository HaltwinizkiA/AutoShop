package ui.action;



import configuration.AutoShopConfiguration;
import facade.AutoShopAdministrator;
import ui.api.IAction;
import ui.menu.controller.MenuController;

import java.lang.module.Configuration;


public class Exit implements IAction {
    @Override
    public void execute() {
        AutoShopConfiguration configuration=new AutoShopConfiguration();
        AutoShopAdministrator.getInstance().saveBoxList(configuration.getGarageList());
        AutoShopAdministrator.getInstance().saveMasterList(configuration.getMasterListPath());
        AutoShopAdministrator.getInstance().saveWorkList(configuration.getWorkList());
        AutoShopAdministrator.getInstance().saveOrderList(configuration.getOrderListPath());
        MenuController menuController = new MenuController();
        menuController.run();
    }
}

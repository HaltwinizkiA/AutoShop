package ui.action;



import ui.api.IAction;
import ui.menu.controller.MenuController;


public class Exit implements IAction {
    @Override
    public void execute() {
        MenuController menuController = new MenuController();
        menuController.run();
    }
}

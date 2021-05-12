package actions;

import api.IAction;
import menu.MenuController.MenuController;

public class Exit implements IAction {
    @Override
    public void execute() {
        MenuController menuController = new MenuController();
        menuController.run();
    }
}

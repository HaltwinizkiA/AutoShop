package ui.builder;

import ui.action.order.*;
import ui.api.Build;
import ui.menu.item.MenuItem;
import ui.menu.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MasterBuilder implements Build, Serializable {
    private final Menu mainMenu;
    private final Menu orderMenu;
    private final Menu masterMenu;
    private final Menu sortOrderMenu;
    private final Menu sortMasterMenu;
    private Menu rootMenu;

    public Menu getRootMenu() {
        return rootMenu;
    }

    public MasterBuilder() {
        this.mainMenu = new Menu();
        this.orderMenu = new Menu();
        this.masterMenu = new Menu();
        this.sortOrderMenu = new Menu();
        this.sortMasterMenu = new Menu();
        this.rootMenu = new Menu();
    }

    @Override
    public void buildMainMenu() {
        List<MenuItem> mainMenuItems = new ArrayList<>();
        mainMenu.setName("Main menu");
        mainMenuItems.add(new MenuItem("Progress Order", new ProgressOrder(), orderMenu));
        mainMenuItems.add(new MenuItem("Canceled Order", new CanceledOrder(), orderMenu));
        mainMenuItems.add(new MenuItem("View all Order", new ViewAllOrders(), orderMenu));
        mainMenuItems.add(new MenuItem("Exit", null, null));
        mainMenu.setMenuItem(mainMenuItems);
        this.rootMenu = mainMenu;

    }
}

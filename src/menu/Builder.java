package menu;

import actions.Exit;
import actions.ViewFreePlace;
import actions.master.*;
import actions.order.*;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    private final Menu mainMenu;
    private final Menu orderMenu;
    private final Menu masterMenu;
    private final Menu sortOrderMenu;
    private final Menu sortMasterMenu;
    private Menu rootMenu;

    public Builder() {
        mainMenu = new Menu();
        orderMenu = new Menu();
        masterMenu = new Menu();
        sortOrderMenu = new Menu();
        sortMasterMenu = new Menu();
        this.rootMenu = new Menu();
    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public void buildMainMenu() {
        List<MenuItem> sortOrderMenuItems = new ArrayList<>();
        sortOrderMenu.setName("Order Sorting");
        sortOrderMenuItems.add(new MenuItem("Sort by Date Complete", new SortOrderDateComplete(), sortOrderMenu));
        sortOrderMenuItems.add(new MenuItem("Sort by Date Create", new SortOrderDateCreate(), sortOrderMenu));
        sortOrderMenuItems.add(new MenuItem("Sort by Price", new SortOrderPrice(), sortMasterMenu));
        sortOrderMenuItems.add(new MenuItem("Sort by Planed Start Date ", new SortOrderPlannedStarDate(), sortMasterMenu));
        sortOrderMenuItems.add(new MenuItem("Exit", new Exit(), sortOrderMenu));
        sortOrderMenu.setMenuItem(sortOrderMenuItems);

        List<MenuItem> orderMenuItems = new ArrayList<>();
        orderMenu.setName("Order Menu");
        orderMenuItems.add(new MenuItem("Add Order", new AddOrder(), orderMenu));
        orderMenuItems.add(new MenuItem("Canceled Order", new CanceledOrder(), orderMenu));
        orderMenuItems.add(new MenuItem("Close Order", new CloseOrder(), orderMenu));
        orderMenuItems.add(new MenuItem("Dell Order", new DellOrder(), orderMenu));
        orderMenuItems.add(new MenuItem("Progress Order", new ProgressOrder(), orderMenu));
        orderMenuItems.add(new MenuItem("Modify Order Planed Start Date",new ModifyPlannedStartDate(),orderMenu));
        orderMenuItems.add(new MenuItem("View all Order", new ViewAllOrders(), orderMenu));
        orderMenuItems.add(new MenuItem("View Order in Progress", new ViewOrderInProgress(), orderMenu));
        orderMenuItems.add(new MenuItem("View Order in Time", new ViewOrderInTime(), orderMenu));
        orderMenuItems.add(new MenuItem("Exit", new Exit(), orderMenu));
        orderMenu.setMenuItem(orderMenuItems);

        List<MenuItem> sortMasterMenuItems = new ArrayList<>();
        sortMasterMenu.setName("Master Sort");
        sortMasterMenuItems.add(new MenuItem("Sort by Name", new SortMasterName(), sortMasterMenu));
        sortMasterMenuItems.add(new MenuItem("Sort by Busy", new SortMasterBusy(), sortMasterMenu));
        sortMasterMenuItems.add(new MenuItem("Exit", new Exit(), sortMasterMenu));
        sortMasterMenu.setMenuItem(sortMasterMenuItems);

        List<MenuItem> masterMenuItems = new ArrayList<>();
        masterMenu.setName("Master Menu");
        masterMenuItems.add(new MenuItem("Add Master", new AddMaster(), masterMenu));
        masterMenuItems.add(new MenuItem("Dell Master", new DellMaster(), masterMenu));
        masterMenuItems.add(new MenuItem("View All Master", new ViewAllMasters(), masterMenu));
        masterMenuItems.add(new MenuItem("View Master In Order", new ViewMasterInOrder(), masterMenu));
        masterMenuItems.add(new MenuItem("EXIT", new Exit(), masterMenu));
        masterMenu.setMenuItem(masterMenuItems);


        List<MenuItem> mainMenuItems = new ArrayList<>();
        mainMenu.setName("Main menu");
        mainMenuItems.add(new MenuItem("Order Menu", null, orderMenu));
        mainMenuItems.add(new MenuItem("Order Sort", null, sortOrderMenu));
        mainMenuItems.add(new MenuItem("Master Menu", null, masterMenu));
        mainMenuItems.add(new MenuItem("Master Sort", null, sortMasterMenu));
        mainMenuItems.add(new MenuItem("View Free Place",new ViewFreePlace(),mainMenu));
        mainMenuItems.add(new MenuItem("Exit", null, null));
        mainMenu.setMenuItem(mainMenuItems);
        this.rootMenu = mainMenu;

    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void setRootMenu(Menu rootMenu) {
        this.rootMenu = rootMenu;
    }
}

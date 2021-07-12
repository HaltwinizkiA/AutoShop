package menu.controller;

import api.Build;
import builder.AdminBuilder;
import builder.ManagerBuilder;
import builder.MasterBuilder;
import connection.Connect;
import navigator.Navigator;
import utility.TextWorker;


public class MenuController {
    public void run() {
        TextWorker worker=new TextWorker();
        Connect.getInstance().getServerConnection();
        worker.println("enter user");
        String user = worker.getStringInput();
        Build builder;

        switch (user) {
            case "admin" -> builder = new AdminBuilder();
            case "master" -> builder = new MasterBuilder();
            case "manager" -> builder = new ManagerBuilder();
            default -> builder = null;

        }

        builder.buildMainMenu();
        Navigator navigator = new Navigator(builder.getRootMenu());

        do {
            navigator.printMenu();

            int select = worker.getIntInput();
            navigator.navigate(select);

        } while (navigator.getCurrentMenu() != null);

    }
}

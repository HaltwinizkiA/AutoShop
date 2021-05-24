package ui.menu.controller;

import ui.api.Build;
import ui.builder.*;
import ui.utils.TextWorker;
import ui.navigator.*;
import java.util.Scanner;
import java.util.WeakHashMap;

public class MenuController {
    public void run() {
        TextWorker worker=new TextWorker();
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

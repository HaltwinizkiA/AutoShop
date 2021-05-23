package ui.menu.controller;



import ui.api.Build;
import ui.builder.AdminBuilder;
import ui.builder.ManagerBuilder;
import ui.builder.MasterBuilder;
import ui.navigator.Navigator;


import java.util.Scanner;

public class MenuController {
    public void run() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter user");
            String user = scanner.next();
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

                int select = scanner.nextInt();
                navigator.navigate(select);

            } while (navigator.getCurrentMenu() != null);

    }
}

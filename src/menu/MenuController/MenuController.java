package menu.MenuController;

import api.Build;
import menu.navigator.Navigator;
import menu.builder.AdminBuilder;
import menu.builder.ManagerBuilder;
import menu.builder.MasterBuilder;
import utils.FileWorker;

import java.util.Scanner;

public class MenuController {
public void run(){
    FileWorker worker=new FileWorker();
    Scanner scanner=new Scanner(System.in);
    System.out.println("enter user");
    String user=scanner.next();
    Build builder;
    switch (user){
        case "admin"->builder=new AdminBuilder();
        case "master"->builder=new MasterBuilder();
        case "manager"->builder=new ManagerBuilder();
        default -> builder=null;

    }
    builder.buildMainMenu();
    Navigator navigator=new Navigator(builder.getRootMenu());

    do {
        navigator.printmenu();

        int select=scanner.nextInt();
        navigator.navigate(select);

    }while (navigator.getCurrentMenu()!=null);

}
}

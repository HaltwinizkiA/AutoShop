package menu;

import java.util.Scanner;

public class MenuController {
public void run(){
    Builder builder=new Builder();
    builder.buildMainMenu();
    Navigator navigator=new Navigator(builder.getRootMenu());
    do {
        navigator.printmenu();
        Scanner scanner=new Scanner(System.in);
        int select=scanner.nextInt();
        navigator.navigate(select);

    }while (navigator.getCurrentMenu()!=null);

}
}

package menu;

import api.Build;
import utils.FileWorker;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

public class MenuController {
public void run(){
    Properties properties=new Properties();
    FileWorker worker=new FileWorker();
    Scanner scanner=new Scanner(System.in);
    try(FileInputStream inputStream=new FileInputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\property.properties")){
        properties.load(inputStream);

    }catch (Exception e){
        worker.logger("properties err "+e);
    }
    System.out.println("enter user");
    String user=scanner.next();
    String file=properties.getProperty(user);
    Build builder=worker.builderReader(file);
    builder.buildMainMenu();
    Navigator navigator=new Navigator(builder.getRootMenu());

    do {
        navigator.printmenu();

        int select=scanner.nextInt();
        navigator.navigate(select);

    }while (navigator.getCurrentMenu()!=null);

}
}

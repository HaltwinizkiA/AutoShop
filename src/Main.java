
import annotation.Specialty;
import api.Build;
import menu.AdminBuilder;
import menu.ManagerBuilder;
import menu.MasterBuilder;
import menu.MenuController;
import model.garage.Garage;
import model.master.Master;
import utils.FileWorker;


import java.io.*;
import java.text.ParseException;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        MenuController menuController = new MenuController();
        menuController.run();
//        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\builders\\MasterBuilder.ser");
//             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
//            Build build=new MasterBuilder();
//            objectOutputStream.writeObject(build);
//            objectOutputStream.close();
//
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//
//
//        }




    }

}

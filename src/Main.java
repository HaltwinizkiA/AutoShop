
import annotation.Specialty;
import api.Build;
import menu.AdminBuilder;
import menu.ManagerBuilder;
import menu.MasterBuilder;
import menu.MenuController;
import model.master.Master;
import utils.FileWorker;


import java.io.*;
import java.text.ParseException;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        MenuController menuController = new MenuController();
        menuController.run();





    }

}

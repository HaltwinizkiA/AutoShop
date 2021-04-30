import actions.master.AddMaster;
import actions.order.AddOrder;
import actions.order.ViewAllOrders;
import api.IAction;
import menu.MenuController;
import model.car.Car;
import model.garage.Garage;
import model.order.Order;
import utils.FileWorker;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        MenuController menuController = new MenuController();
        menuController.run();




    }

}

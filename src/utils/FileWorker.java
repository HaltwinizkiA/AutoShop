package utils;

import api.Build;
import model.garage.Garage;
import model.master.Master;
import model.order.Order;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class FileWorker {


    public List<Master> mastersReader() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\masterList.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {


            List<Master> masters = (List<Master>) objectInputStream.readObject();
            return masters;

        } catch (Exception e) {
            logger(e.toString());
            return null;
        }

    }
    public Build builderReader(String file){
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {


            Build builder = (Build) objectInputStream.readObject();
            return builder;

        } catch (Exception e) {
            logger(e.toString());
            return null;
        }


    }
    public boolean masterWriter(List<Master> masters) {
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\masterList.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(masters);
            objectOutputStream.close();
            return true;

        } catch (Exception e) {
            logger(e.toString());
            return false;
        }
    }

    public List<Order> ordersRead() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\orderList.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            List<Order> orders = (List<Order>) objectInputStream.readObject();
            return orders;
        } catch (Exception e) {
            logger(e.toString());
            return null;
        }
    }

    public boolean ordersWriter(List<Order> orders) {
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\orderList.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(orders);
            objectOutputStream.close();
            return true;

        } catch (Exception e) {
            logger(e.toString());
            return false;
        }
    }

    public Garage garageReader() {
        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\garage.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {


            Garage garages = (Garage) objectInputStream.readObject();
            return garages;
        } catch (Exception e) {

            logger(e.toString());
            return null;
        }

    }

    public boolean garageWriter(Garage garages) {
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\37533\\Projects\\AutoShop\\src\\db\\garage.ser");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(garages);
            objectOutputStream.close();
            return true;

        } catch (Exception e) {
            logger(e.toString());

            return false;
        }


    }

    public boolean logger(String string) {
        File file = new File("C:\\Users\\37533\\Projects\\AutoShop\\src\\db", "logs.txt");
        try (FileWriter writer = new FileWriter(file,true)) {
            writer.write(string+"\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

}

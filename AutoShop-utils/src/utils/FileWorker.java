package utils;

import javax.management.BadStringOperationException;
import java.io.*;
import java.util.Properties;

public class FileWorker {


    public Properties getProperties(String path) {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            logger("properties err " + e);
            return null;
        }
    }

    public void writer(Object object, String path) {
        try (FileOutputStream outputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

            objectOutputStream.writeObject(object);
            objectOutputStream.close();


        } catch (Exception e) {
            logger(e.toString());


        }

    }

    public <T> T lehaReader(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object object = objectInputStream.readObject();

            return (T) object;
        } catch (Exception e) {

            logger(e.toString());
            return null;
        }


    }


    public Object reader(String path) {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object object = objectInputStream.readObject();
            return object;
        } catch (Exception e) {

            logger(e.toString());
            return null;
        }
    }


    public boolean logger(String string) {
        File file = new File("C:\\Users\\37533\\Projects\\AutoShop\\logs.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(string + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

}

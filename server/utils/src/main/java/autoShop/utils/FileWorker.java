package autoShop.utils;





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
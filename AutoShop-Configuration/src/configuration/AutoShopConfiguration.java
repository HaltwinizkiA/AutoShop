package configuration;


import utils.FileWorker;

import java.util.Properties;

public class AutoShopConfiguration {
    private final FileWorker fileWorker;
    private final Properties properties;


    public AutoShopConfiguration() {
        fileWorker = new FileWorker();
        properties = fileWorker.getProperties("C:\\Users\\37533\\Projects\\AutoShop\\AutoShop-Configuration\\src\\resources\\property.properties");

    }

    public String getOrderListPath() {
        return properties.getProperty("orderList");
    }

    public String getMasterListPath() {
        return properties.getProperty("masterList");
    }

    public String getWorkListPath() {
        return properties.getProperty("workList");
    }

    public String getBoxListPath() {
        return properties.getProperty("boxList");
    }

    public String getWorkCSVPath() {
        return properties.getProperty("workCSV");
    }

    public String getOrderCSVPath() {
        return properties.getProperty("ordersCSV");
    }

    public String getMasterCSVPath() {
        return properties.getProperty("mastersCSV");
    }

    public String getBoxCSVPath() {
        return properties.getProperty("boxCSV");
    }

}

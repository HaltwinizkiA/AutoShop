package autoShop.daoImpl.connect;


import autoShop.configuration.AutoShopConfiguration;
import autoShop.utils.FileWorker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
    private static final FileWorker fileWorker = new FileWorker();
    private static DbConnect dbConnector;
    private final AutoShopConfiguration configuration = new AutoShopConfiguration();
    private Connection connection;

    public static DbConnect getInstance() {
        if (dbConnector == null) {
            dbConnector = new DbConnect();
        }
        return dbConnector;
    }

    public DbConnect(){
        connect();;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(configuration.getUrl(), configuration.getName(), configuration.getPassword());
        } catch (Exception e) {
            fileWorker.logger("data base connect error  " + e);
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }
}

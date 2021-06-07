package runner;

import facade.AutoShopAdministrator;
import server.Server;


public class Runner {
    public static void main(String[] args) {
        AutoShopAdministrator.getInstance();
        Server server = new Server();
        server.start();
    }
}

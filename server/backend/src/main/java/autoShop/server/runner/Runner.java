package autoShop.server.runner;


import autoShop.facade.AutoShopAdministrator;
import autoShop.server.server.Server;

public class Runner {
    public static void main(String[] args) {
        AutoShopAdministrator.getInstance();
        Server server = new Server();
        server.start();
    }
}

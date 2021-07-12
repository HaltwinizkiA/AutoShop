package autoShop.server.server;


import autoShop.facade.AutoShopAdministrator;
import autoShop.server.thread.ServerThread;
import autoShop.utils.FileWorker;
import autoShop.utils.TextWorker;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final AutoShopAdministrator autoShopAdministrator = AutoShopAdministrator.getInstance();
    private final FileWorker fileWorker = new FileWorker();
    private final TextWorker textWorker = new TextWorker();

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(1488);
            while (true) {

                Socket socket = serverSocket.accept();
                textWorker.println("client joined");
                ServerThread serverThread = new ServerThread(socket, autoShopAdministrator);
                serverThread.start();
            }
        } catch (Exception e) {
            fileWorker.logger("server connect error" + e);
        }

    }
}

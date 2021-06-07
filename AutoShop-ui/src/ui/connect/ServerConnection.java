package ui.connect;

import ui.utils.TextWorker;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {
    public static ServerConnection connection;
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private static final TextWorker textWorker = new TextWorker();

    private ServerConnection() throws IOException {

        this.socket = new Socket("localhost", 1488);
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public static ServerConnection getInstance() {
        if (connection == null) {
            try {
                connection = new ServerConnection();
            } catch (IOException e) {
                textWorker.println("Server Connection error"+ e);
            }
        }
        return connection;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void closeConnection() {
        try {
            this.socket.close();
            this.inputStream.close();
        } catch (IOException e) {
            textWorker.println("Client error"+ e);
        }
    }
}

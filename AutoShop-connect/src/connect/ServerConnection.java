package connect;

import utils.FileWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {
    public static ServerConnection connection;
    private final Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private static final FileWorker fileWorker = new FileWorker();

    private ServerConnection() throws IOException {

        this.socket = new Socket("localhost", 1488);
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public static ServerConnection getInstance() {
        if (connection == null) {
            try {
                System.out.println("2");
                connection = new ServerConnection();
                System.out.println("123");
            } catch (IOException e) {
                fileWorker.logger("Server Connection error"+ e);
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
            fileWorker.logger("Client error"+ e);
        }
    }
}

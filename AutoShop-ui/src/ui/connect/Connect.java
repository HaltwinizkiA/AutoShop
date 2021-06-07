package ui.connect;

import ui.utils.TextWorker;

public class Connect {
    public static Connect connect;

    private ServerConnection serverConnection;
    private final TextWorker textWorker;

    private Connect() {
        this.textWorker = new TextWorker();
    }

    public static Connect getInstance() {
        if (connect == null) {
            connect = new Connect();

        }
        return connect;
    }

    public void getServerConnection() {
        serverConnection = ServerConnection.getInstance();
    }

    public void send(String methodName, String... arguments) {
        String message = methodName + "/";
        for (String argument : arguments) {
            message = message + argument + ";";
        }
        try {
            serverConnection.getOutputStream().writeObject(message);
            serverConnection.getOutputStream().flush();
            textWorker.println((String) serverConnection.getInputStream().readObject()+"\n");
        } catch (Exception e) {
            textWorker.println("connect Error");

        }

    }

    public void send(String methodName) {
        try {
            serverConnection.getOutputStream().writeObject(methodName);
            serverConnection.getOutputStream().flush();
            textWorker.println((String) serverConnection.getInputStream().readObject());
        } catch (Exception e) {
            textWorker.println("connect Error");

        }
    }

    public void closeConnection() {
        serverConnection.closeConnection();
    }


}

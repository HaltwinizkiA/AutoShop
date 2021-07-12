package autoShop.server.thread;


import autoShop.facade.AutoShopAdministrator;
import autoShop.utils.FileWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private final ObjectInputStream input;
    private final ObjectOutputStream output;
    private final FileWorker fileWorker;
    private AutoShopAdministrator autoShopAdministrator;
    public ServerThread(Socket socket, AutoShopAdministrator autoShopAdministrator) throws IOException {
        fileWorker = new FileWorker();
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        this.autoShopAdministrator=autoShopAdministrator;
    }

    @Override
    public void run() {
        String importLine="";
        try {
            while ((importLine = (String) input.readObject()) != "" ){
                output.writeObject(handler(importLine));
            }

        } catch (Exception e) {
            fileWorker.logger("read object error" + e);
        } finally {
            disconnect();
        }
    }

    private void disconnect() {
        try {
            output.close();
            input.close();
        } catch (IOException e) {
            fileWorker.logger("server disconnect error" + e);
        } finally {
            this.interrupt();
        }

    }
    private String handler(String line) {
        String[] splitLine = line.split("/");
        String methodName = splitLine[0];
        try {
            if (splitLine.length > 1) {
            String[] arguments=splitLine[1].split(";");
                return (String) this.autoShopAdministrator.getClass().getMethod(methodName,getArguments(arguments)).invoke(this.autoShopAdministrator,(Object[]) arguments);
            } else
                return (String) this.autoShopAdministrator.getClass().getMethod(methodName).invoke(this.autoShopAdministrator);

        }catch (Exception e){
            fileWorker.logger("server Thread error  "+this.getName()+" "+e);
            return null;
        }
    }
    private Class[] getArguments(String[] arr){
        Class[] arguments=new Class[arr.length];
        for (int i=0;i<arguments.length;i++) {
            arguments[i]=(arr[i].getClass());
        }
        return arguments;
    }

}

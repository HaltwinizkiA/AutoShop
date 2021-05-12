package actions.master;

import api.IAction;
import model.master.Master;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DellMaster implements IAction {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter name ");
        String name = scanner.nextLine();
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("masterList");
        List<Master> masters = (List<Master>) worker.reader(path);
        for (int i = 0; i < masters.size(); i++) {
            if (masters.get(i).getName().equals(name)) {
                masters.remove(i);
                break;
            }
        }
        worker.writer(masters, path);


    }
}

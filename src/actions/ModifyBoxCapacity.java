package actions;

import api.IAction;
import model.garage.Garage;
import utils.FileWorker;

import java.util.Properties;
import java.util.Scanner;

public class ModifyBoxCapacity implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("garage");
        Garage garage = (Garage) worker.reader(path);
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter capacity");
        int capacity = scanner.nextInt();
        garage.setCapacity(capacity);
        worker.writer(garage, path);

    }
}

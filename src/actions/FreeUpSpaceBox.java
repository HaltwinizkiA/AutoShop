package actions;

import api.IAction;
import model.garage.Garage;
import utils.FileWorker;

import java.util.Properties;
import java.util.Scanner;

public class FreeUpSpaceBox implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("garage");
        Garage garage = (Garage) worker.reader(path);
        for (int i = 0; i < garage.getCars().length; i++) {
            System.out.println("BOX " + i + ": " + garage.getCars()[i]);
        }
        System.out.println("select BOX num");
        Scanner scanner = new Scanner(System.in);
        int boxNum = scanner.nextInt();
        try {
            garage.getCars()[boxNum] = null;
        } catch (Exception e) {
            worker.logger("garage exp :" + e);
        }
        worker.writer(garage, path);
    }
}

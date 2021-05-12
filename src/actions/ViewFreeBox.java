package actions;

import api.IAction;
import model.garage.Garage;
import utils.FileWorker;

import java.util.Properties;

public class ViewFreeBox implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("garage");
        Garage garage = (Garage) worker.reader(path);
        int countFreePlace = 0;

        for (int i = 0; i < garage.getCars().length; i++) {
            System.out.println("BOX " + i + ": " + garage.getCars()[i]);
            if (garage.getCars()[i] == null) {
                countFreePlace++;
            }

        }
        System.out.println("Count of free place : " + countFreePlace + "\n");
    }
}

package actions;

import api.IAction;
import model.garage.Garage;
import utils.FileWorker;

import java.util.Scanner;

public class ModifyBoxCapacity implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        Garage garage=worker.garageReader();
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter capacity");
        int capacity=scanner.nextInt();
        garage.setCapacity(capacity);
        worker.garageWriter(garage);

    }
}

package actions;

import api.IAction;
import model.garage.Garage;
import utils.FileWorker;

public class ViewFreePlace implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        Garage garage=worker.garageReader();
        int countFreePlace=0;
        for (int i=0;i<garage.getCars().length;i++){

            if (garage.getCars()[i]==null){
                countFreePlace++;
            }

        }
        System.out.println("Count of free place : "+countFreePlace);
    }
}

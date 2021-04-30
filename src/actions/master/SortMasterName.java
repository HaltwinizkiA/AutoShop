package actions.master;

import api.IAction;
import comporators.master.MasterNameSort;
import model.master.Master;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;

public class SortMasterName implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Master> masters= worker.mastersReader();
        Collections.sort(masters,new MasterNameSort());
        worker.masterWriter(masters);
    }
}

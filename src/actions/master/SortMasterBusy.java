package actions.master;

import api.IAction;
import comporators.master.MasterBusySort;
import comporators.master.MasterNameSort;
import model.master.Master;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;

public class SortMasterBusy implements IAction{
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        List<Master> masters= worker.mastersReader();
        Collections.sort(masters,new MasterBusySort());
        worker.masterWriter(masters);
    }
}

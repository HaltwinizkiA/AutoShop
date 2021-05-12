package actions.master;

import api.IAction;
import comporators.master.MasterBusySort;
import model.master.Master;
import utils.FileWorker;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SortMasterBusy implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("masterList");
        List<Master> masters = (List<Master>) worker.reader(path);
        Collections.sort(masters, new MasterBusySort());
        worker.writer(masters, path);
    }
}

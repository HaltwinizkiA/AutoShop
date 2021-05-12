package actions.master;

import api.IAction;
import model.master.Master;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;

public class ViewAllMasters implements IAction {
    @Override
    public void execute() {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("masterList");
        List<Master> masters = (List<Master>) worker.reader(path);
        for (int i = 0; i < masters.size(); i++) {
            System.out.println(i + " " + masters.get(i).toString());
        }
    }
}

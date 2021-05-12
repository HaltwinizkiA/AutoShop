package actions.work;

import api.IAction;
import model.work.Work;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;

public class ViewWorkList implements IAction{
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
        Properties properties=worker.getProperties();
        String path=properties.getProperty("workList");
        List<Work> workList= (List<Work>) worker.reader(path);
        for (int i=0;i<workList.size();i++){
        System.out.println(i+" "+workList.get(i));
        }
    }
}

package actions.master;

import api.IAction;
import model.master.Master;
import utils.FileWorker;

import java.util.List;

public class ViewAllMasters implements IAction {
    @Override
    public void execute() {
        FileWorker worker=new FileWorker();
       List<Master> masters= worker.mastersReader();
       for (int i=0;i<masters.size();i++){
           System.out.println(i+" "+masters.get(i).toString());
       }
    }
}

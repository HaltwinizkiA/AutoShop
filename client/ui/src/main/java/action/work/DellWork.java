package action.work;



import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class DellWork implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        Connect.getInstance().send("viewWorkList");
        worker.println("enter work num");
        String num=worker.getStringLine();
        Connect.getInstance().send("viewWorkList");
        Connect.getInstance().send("dellWork",num);


    }
}

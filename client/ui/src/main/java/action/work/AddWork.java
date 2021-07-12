package action.work;

import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class AddWork implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
                Connect.getInstance().send("viewWorkList");
        worker.println("enter work name");
        String workName=worker.getStringInput();
        worker.println("enter price ");
        String price=worker.getStringInput();
        Connect.getInstance().send("addWork",workName,price);




    }
}

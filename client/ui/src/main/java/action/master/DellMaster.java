package action.master;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class DellMaster implements IAction {

    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        Connect.getInstance().send("viewAllMaster");
        worker.println("enter master num");
        String num=worker.getStringInput();

        Connect.getInstance().send("dellMaster",num);


    }
}

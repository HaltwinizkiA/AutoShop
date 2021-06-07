package ui.action.master;


import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


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

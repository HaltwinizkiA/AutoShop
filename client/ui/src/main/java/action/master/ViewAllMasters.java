package action.master;

import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class ViewAllMasters implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("viewAllMaster");

    }
}

package action.master;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class SortMasterName implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("masterNameSort");
        Connect.getInstance().send("viewAllMaster");
    }
}

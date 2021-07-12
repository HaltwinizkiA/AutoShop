package action.master;

import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class SortMasterBusy implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("masterBusySort");
        Connect.getInstance().send("viewAllMaster");


    }
}

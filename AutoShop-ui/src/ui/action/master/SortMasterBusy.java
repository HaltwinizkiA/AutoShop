package ui.action.master;


import ui.api.IAction;
import ui.connect.Connect;



public class SortMasterBusy implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("masterBusySort");
        Connect.getInstance().send("viewAllMaster");


    }
}

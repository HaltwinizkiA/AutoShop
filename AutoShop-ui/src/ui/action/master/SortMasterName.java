package ui.action.master;


import ui.api.IAction;
import ui.connect.Connect;



public class SortMasterName implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("masterNameSort");
        Connect.getInstance().send("viewAllMaster");
    }
}

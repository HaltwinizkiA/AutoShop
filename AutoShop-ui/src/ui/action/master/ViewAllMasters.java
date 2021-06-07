package ui.action.master;

import ui.api.IAction;
import ui.connect.Connect;

public class ViewAllMasters implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("viewAllMaster");

    }
}

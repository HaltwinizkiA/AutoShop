package ui.action.csv;


import ui.api.IAction;
import ui.connect.Connect;

public class WorkRead implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("csvWorkRead");
    }
}

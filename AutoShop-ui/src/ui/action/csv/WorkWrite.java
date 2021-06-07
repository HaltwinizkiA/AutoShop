package ui.action.csv;


import ui.api.IAction;
import ui.connect.Connect;

public class WorkWrite implements IAction {

    @Override
    public void execute() {
        Connect.getInstance().send("csvWorkWrite");
    }
}

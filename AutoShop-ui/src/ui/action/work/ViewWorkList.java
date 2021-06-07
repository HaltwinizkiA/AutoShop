package ui.action.work;

import ui.api.IAction;
import ui.connect.Connect;



public class ViewWorkList implements IAction {
    @Override
    public void execute() {

        Connect.getInstance().send("viewWorkList");


    }
}

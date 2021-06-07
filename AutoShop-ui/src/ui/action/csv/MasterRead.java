package ui.action.csv;


import ui.api.*;
import ui.connect.Connect;

public class MasterRead implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("csvMasterListRead");
    }
}

package action.work;

import api.IAction;
import connection.Connect;
import utility.TextWorker;

public class ViewWorkList implements IAction {
    @Override
    public void execute() {

        Connect.getInstance().send("viewWorkList");


    }
}

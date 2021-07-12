package action.csv;


import api.IAction;
import connection.Connect;
import utility.TextWorker;
public class OrderRead implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("csvOrderListRead");
    }
}

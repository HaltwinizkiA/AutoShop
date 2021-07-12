package action.box;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class ViewFreeBox implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        textWorker.println("enter box num");
        String boxNum = textWorker.getStringInput();
        Connect.getInstance().send("viewFreeBox", boxNum);

    }
}

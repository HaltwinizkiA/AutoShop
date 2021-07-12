package action.box;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class FreeUpSpaceBox implements IAction {
    @Override
    public void execute() {

        TextWorker textWorker = new TextWorker();
        Connect.getInstance().send("viewAllCarInBox");
        textWorker.println("enter box num");
        String boxNum = textWorker.getStringInput();
        Connect.getInstance().send("freeUpSpaceBox",boxNum);


    }
}

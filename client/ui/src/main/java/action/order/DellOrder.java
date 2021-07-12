package action.order;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class DellOrder implements IAction {
    @Override
    public void execute() {

        TextWorker textWorker = new TextWorker();
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter order num");
        String num = textWorker.getStringInput();
        Connect.getInstance().send("dellOrder", num);


    }
}

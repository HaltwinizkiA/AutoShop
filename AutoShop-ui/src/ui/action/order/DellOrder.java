package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


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

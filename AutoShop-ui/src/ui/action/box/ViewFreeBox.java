package ui.action.box;



import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


public class ViewFreeBox implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        textWorker.println("enter box num");
        String boxNum = textWorker.getStringInput();
        Connect.getInstance().send("viewFreeBox", boxNum);

    }
}

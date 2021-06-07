package ui.action.master;


import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


public class ViewMasterInOrder implements IAction {
    @Override
    public void execute() {
        TextWorker worker = new TextWorker();
        Connect.getInstance().send("viewAllOrder");
        worker.println("enter order num");
        String num = worker.getStringInput();
        Connect.getInstance().send("viewMasterInOrder", num);

    }
}

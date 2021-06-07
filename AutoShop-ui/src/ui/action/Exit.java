package ui.action;





import ui.api.IAction;
import ui.connect.Connect;



public class Exit implements IAction {
    @Override
    public void execute() {
        Connect.getInstance().send("save");
        Connect.getInstance().closeConnection();
    }
}

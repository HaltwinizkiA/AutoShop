package ui.action.box;




import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


public class ModifyBoxCapacity implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        Connect.getInstance().send("viewAllCarInBox");
        textWorker.println("enter box num");
        String boxNum=textWorker.getStringInput();
        textWorker.println("enter new capacity");
        String newCapacity=textWorker.getStringInput();
        Connect.getInstance().send("modifyBoxCapacity",boxNum,newCapacity);

    }
}

package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;



public class ModifyPlannedStartDate implements IAction {


    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter order num");
        String num=textWorker.getStringInput();
        System.out.println("planned start work date in format - HH:mm dd/MM/yy " );
        String plannedStartDate = textWorker.getStringLine();
        Connect.getInstance().send("modifyPlannedStartDate",num, plannedStartDate);

    }
}

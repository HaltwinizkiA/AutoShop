package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;




public class AddOrder implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        textWorker.println("enter car id");
        String carId=textWorker.getStringInput();
        textWorker.println("owner name:");
        String owner = textWorker.getStringInput();
        textWorker.println("planned start work date in format - yyyy-MM-dd ");
        String date = textWorker.getStringLine();
        textWorker.println("enter id ");
        Connect.getInstance().send("addOrder",date,carId,owner);







    }
}

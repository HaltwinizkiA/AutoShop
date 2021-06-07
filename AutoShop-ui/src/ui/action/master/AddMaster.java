package ui.action.master;



import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


public class AddMaster implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        Connect.getInstance().send("viewAllMaster");
        worker.println("enter master name");
        String name=worker.getStringInput();
        worker.println("enter Date of birth");
        String dateOfBirth=worker.getStringInput();
        worker.println("enter phone number");
        String phoneNumber=worker.getStringInput();
        worker.println("enter specialty");
        String specialty=worker.getStringInput();
        worker.println("enter id ");
        String id=worker.getStringInput();

        Connect.getInstance().send("addMaster",name,dateOfBirth,phoneNumber,specialty,id);

    }
}

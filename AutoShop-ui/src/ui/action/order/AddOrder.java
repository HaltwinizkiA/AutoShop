package ui.action.order;



import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;




public class AddOrder implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter car:\nmark-");
        String mark = textWorker.getStringInput();
        textWorker.println("model");
        String model = textWorker.getStringInput();
        textWorker.println("color");
        String color = textWorker.getStringInput();
        textWorker.println("number");
        textWorker.println("id");
        String carId=textWorker.getStringInput();
        String number = textWorker.getStringInput();
        textWorker.println("owner name:");
        String owner = textWorker.getStringInput();
        textWorker.println("planned start work date in format - HH:mm dd/MM/yy ");
        String date = textWorker.getStringLine();


        textWorker.println("enter id ");
        String id=textWorker.getStringLine();
        Connect.getInstance().send("addOrder",date,mark,model,color,number,carId,owner,id);
        Connect.getInstance().send("viewWorkList");
        textWorker.println("enter works numbers ");
        String line="";
        while (textWorker.getStringLine()!=null){
            line=line+"/"+textWorker.getStringLine();
        }
        String[] arguments=line.split("/");
        Connect.getInstance().send("addWorkToOrder",arguments);






    }
}

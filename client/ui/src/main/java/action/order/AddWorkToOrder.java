package action.order;


import api.IAction;
import connection.Connect;
import utility.TextWorker;
public class AddWorkToOrder implements IAction {


    @Override
    public void execute() {

        TextWorker textWorker=new TextWorker();
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter order id");
        String orderId=textWorker.getStringInput();
        while (true) {
            Connect.getInstance().send("viewWorkList");
            textWorker.println("enter works number ");
            String workNum=textWorker.getStringInput();
            if (workNum.equals("stop")){
                break;
            }

            Connect.getInstance().send("addWorkToOrder", workNum);
        }
    }
}

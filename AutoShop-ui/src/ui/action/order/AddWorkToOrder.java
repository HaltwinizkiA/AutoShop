package ui.action.order;



import ui.api.IAction;

import ui.connect.Connect;
import ui.utils.TextWorker;

public class AddWorkToOrder implements IAction {


    @Override
    public void execute() {
        TextWorker textWorker=new TextWorker();
        textWorker.println("enter order id");
        String orderId=textWorker.getStringInput();
        Connect.getInstance().send("viewWorkList");
        textWorker.println("enter works numbers ");
        String line=orderId+"/";
        while (textWorker.getStringLine()!=null){
            line=line+"/"+textWorker.getStringLine();
        }
        String[] arguments=line.split("/");

        Connect.getInstance().send("addWorkToOrder  rkToOrder",arguments);

    }
}

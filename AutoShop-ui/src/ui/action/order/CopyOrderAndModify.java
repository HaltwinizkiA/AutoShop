package ui.action.order;



import ui.api.IAction;

import ui.connect.Connect;
import ui.utils.TextWorker;
public class CopyOrderAndModify implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        String orderId="";
        String carId="";
        String ownersName ="";
        String plannedStartDate = null;
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter order id");
        orderId = textWorker.getStringInput();
        while (true) {
            textWorker.println("enter field number to modify \n1) car\n2) owner's name\n3) planned start date /");
            switch (textWorker.getIntInput()) {
                case 1:

                    textWorker.println("enter id ");
                    carId=textWorker.getStringInput();
                    break;
                case 2:
                    textWorker.println("enter owner's name ");
                    ownersName = textWorker.getStringInput();
                    break;

                case 3:
                    textWorker.println("planned start work date in format - yyyy-MM-dd ");
                    plannedStartDate=textWorker.getStringLine();
                    }
            break;
        }
        Connect.getInstance().send("copyOrderAndModify",orderId,carId,ownersName,plannedStartDate);

    }
}

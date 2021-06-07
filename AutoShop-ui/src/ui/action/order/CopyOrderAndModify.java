package ui.action.order;



import ui.api.IAction;

import ui.connect.Connect;
import ui.utils.TextWorker;
public class CopyOrderAndModify implements IAction {
    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        String carMark="";
        String carmodel="";
        String carcolor="";
        String carnumber="";
        String carcarId="";
        String ownersName ="";
        String masterName = null;
        String plannedStartDate = null;
        String price = null;
        Connect.getInstance().send("viewAllOrder");
        textWorker.println("enter order num");
        String orderNum = textWorker.getStringInput();
        while (true) {
            textWorker.println("enter field number to modify \n1) car\n2) owner's name\n3) master\n4) planned start date /");
            switch (textWorker.getIntInput()) {
                case 1:
                    textWorker.println("enter mark");
                    String mark = textWorker.getStringInput();
                    textWorker.println("enter model");
                    String model = textWorker.getStringInput();
                    textWorker.println("enter color");
                    String color = textWorker.getStringInput();
                    textWorker.println("enter number");
                    String number = textWorker.getStringInput();
                    textWorker.println("enter id ");
                    Integer carId=textWorker.getIntInput();
                    break;
                case 2:
                    textWorker.println("enter owner's name ");
                    ownersName = textWorker.getStringInput();
                    break;

                case 3:
                    textWorker.println("enter master name");
                    masterName = textWorker.getStringInput();
                    break;

                case 4:
                    textWorker.println("planned start work date in format - HH:mm dd/MM/yy ");
                    plannedStartDate=textWorker.getStringLine();

                    }
            break;
        }
        textWorker.println("enter id ");
        String id=textWorker.getStringInput();
        Connect.getInstance().send("copyOrderAndModify",orderNum,carMark,carmodel,carcolor,carnumber,carcarId,ownersName,masterName,plannedStartDate,id);

    }
}

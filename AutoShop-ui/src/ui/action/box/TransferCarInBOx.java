package ui.action.box;


import ui.api.IAction;
import ui.connect.Connect;
import ui.utils.TextWorker;


public class TransferCarInBOx implements IAction {

    @Override
    public void execute() {
        TextWorker textWorker = new TextWorker();
        textWorker.println("enter box id");
        String boxId=textWorker.getStringInput();
        textWorker.println("enter car id");
        String carId=textWorker.getStringInput();

        Connect.getInstance().send("transferCarInBox",boxId,carId);
    }
}

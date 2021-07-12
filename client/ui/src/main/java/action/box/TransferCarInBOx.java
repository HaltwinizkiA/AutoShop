package action.box;


import api.IAction;
import connection.Connect;
import utility.TextWorker;


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

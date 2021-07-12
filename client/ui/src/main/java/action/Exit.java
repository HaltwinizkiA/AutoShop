package action;

import api.IAction;
import connection.Connect;
import utility.TextWorker;


public class Exit implements IAction {
    @Override
    public void execute() {
       TextWorker textWorker =new TextWorker();
       textWorker.println("bye");

    }
}

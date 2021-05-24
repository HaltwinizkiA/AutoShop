package ui.action.work;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import utils.TextWorker;


public class DellWork implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().viewWorkList();
        worker.println("enter work num");
        int num=worker.getIntInput();
        AutoShopAdministrator.getInstance().dellWork(num);

    }
}

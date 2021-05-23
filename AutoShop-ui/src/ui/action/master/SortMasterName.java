package ui.action.master;



import facade.AutoShopAdministrator;
import ui.api.IAction;
import utils.TextWorker;


public class SortMasterName implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().masterNameSort();
        AutoShopAdministrator.getInstance().viewAllMaster();
    }
}

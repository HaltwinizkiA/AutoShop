package ui.action.work;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import ui.utils.TextWorker;

public class ViewWorkList implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().viewWorkList();


    }
}

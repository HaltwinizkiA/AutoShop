package ui.action.work;


import facade.AutoShopAdministrator;
import ui.api.IAction;
import ui.utils.TextWorker;


public class AddWork implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().viewWorkList();
        worker.println("enter work name");
        String workName=worker.getStringInput();
        worker.println("enter price ");
        double price=worker.getDoubleInput();
        worker.println("enter id ");
        Integer id=worker.getIntInput();
        AutoShopAdministrator.getInstance().addWork(workName,price,id);



    }
}

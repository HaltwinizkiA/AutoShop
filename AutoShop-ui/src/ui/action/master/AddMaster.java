package ui.action.master;


import facade.AutoShopAdministrator;
import model.enums.Specialty;
import ui.api.IAction;
import utils.TextWorker;


public class AddMaster implements IAction {
    @Override
    public void execute() {
        TextWorker worker=new TextWorker();
        AutoShopAdministrator.getInstance().viewAllMaster();
        worker.println("enter master name");
        String name=worker.getStringInput();
        worker.println("enter Date of birth");
        String dateOfBirth=worker.getStringInput();
        worker.println("enter phone number");
        String phoneNumber=worker.getStringInput();
        Specialty[] specialties = Specialty.values();
        for (int i = 0; i < Specialty.values().length; i++) {
            System.out.println("# " + i + " " + specialties[i]);
        }
        int numOfSpecialty = worker.getIntInput();
        worker.println("enter id ");
        Integer id=worker.getIntInput();
        AutoShopAdministrator.getInstance().addMaster(name,dateOfBirth,phoneNumber,specialties[numOfSpecialty],id);

    }
}

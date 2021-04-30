package actions.master;

import annotation.Specialty;
import api.IAction;
import model.master.Master;
import utils.FileWorker;

import java.util.List;
import java.util.Scanner;

public class AddMaster implements IAction {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter name:");
        String name = scanner.nextLine();
        System.out.println("enter date of birth");
        String dateOfBirth = scanner.nextLine();
        System.out.println("phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("enter num of specialty:");
        Specialty[] specialties = Specialty.values();
        for (int i = 0; i < Specialty.values().length; i++) {
            System.out.println("# " + i + " " + specialties[i]);
        }
        int numOfSpecialty = scanner.nextInt();
        try {
            Master master = new Master(name, dateOfBirth, phoneNumber, specialties[numOfSpecialty]);
            FileWorker worker = new FileWorker();
            List<Master> masterList = worker.mastersReader();
            masterList.add(master);
            worker.masterWriter(masterList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package actions.work;

import api.IAction;
import model.work.Work;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AddWork implements IAction {
    @Override
    public void execute() {


        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("workList");
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter working name");
        String name = scanner.nextLine();
        double price;
        while (true) {
            System.out.println("enter price");

            try {
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                worker.logger(e.toString());
            }
        }
        Work work = new Work(name, price);
        List<Work> workList = (List<Work>) worker.reader(path);
        workList.add(work);
        worker.writer(workList, path);


    }
}

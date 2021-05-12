package actions.work;

import api.IAction;
import model.work.Work;
import utils.FileWorker;

import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class DellWork implements IAction {
    @Override
    public void execute() {
        Scanner scanner=new Scanner(System.in);
        FileWorker worker=new FileWorker();
        Properties properties= worker.getProperties();
        String path=properties.getProperty("workList");
        List<Work> workList= (List<Work>) worker.reader(path);
        for (int i=0;i<workList.size();i++){
            System.out.println(i+" "+workList.get(i));
        }
        System.out.println("select num for remove");
        int numForRemove=scanner.nextInt();
        workList.remove(numForRemove);
        worker.writer(workList,path);

    }
}

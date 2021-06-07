import configuration.AutoShopConfiguration;
import facade.AutoShopAdministrator;
import model.entity.work.Work;
import utils.FileWorker;


import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        List<Work> workList = new ArrayList<>();
        workList.add(new Work("change oil", 40.5, 001));
        workList.add(new Work("change wheels", 20.0, 002));
        workList.add(new Work("paint", 100, 003));
        FileWorker fileWorker=new FileWorker();
        AutoShopConfiguration autoShopConfiguration=new AutoShopConfiguration();
        fileWorker.writer(workList,autoShopConfiguration.getWorkListPath() );
    }
}
import configuration.AutoShopConfiguration;
import model.entity.car.Car;
import model.entity.garage.Garage;
import model.entity.master.Master;
import model.entity.order.Order;
import model.entity.work.Work;


import model.enums.Specialty;
import utils.FileWorker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;

public class Main {

    public static void main(String[] args) {

        AutoShopConfiguration autoShopConfiguration=new AutoShopConfiguration();

        FileWorker worker=new FileWorker();
 List<Work> workList=new ArrayList<>();
 workList.add(new Work("change oil",40.5,001));
 workList.add(new Work("change wheels",20.0,002));

 List<Order> orderList=new ArrayList<>();
 List<Master> masterList=new ArrayList<>();
 masterList.add(new Master("Lesha","19.06.1999","+34234234", Specialty.ELECTRIC,001));
 masterList.add(new Master("Maxim","12.02.2005","+123123123",Specialty.DETAILING,002));
 Order order=new Order(new Date(),new Date(),new Car("ford","escort","green","1234",1235),"sasha",workList,60.5,001);
 orderList.add(order);
List<Garage> boxList=new ArrayList<>();
boxList.add(new Garage(5,001));
worker.writer(boxList,autoShopConfiguration.getBoxListPath());
worker.writer(orderList,autoShopConfiguration.getOrderListPath());
worker.writer(masterList,autoShopConfiguration.getMasterListPath());




    }
}
// List<Work> workList=new ArrayList<>();
// workList.add(new Work("change oil",40.5,001));
// workList.add(new Work("change wheels",20.0,002));
// workList.add(new Work("paint",100,003));
// worker.writer(workList,autoShopConfiguration.getWorkList());
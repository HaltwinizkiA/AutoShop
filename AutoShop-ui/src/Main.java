import configuration.AutoShopConfiguration;
import model.entity.car.Car;
import model.entity.order.Order;
import model.entity.work.Work;
import ui.menu.controller.MenuController;
import ui.utils.TextWorker;
import utils.FileWorker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;

public class Main {

    public static void main(String[] args) {

        AutoShopConfiguration autoShopConfiguration=new AutoShopConfiguration();

        FileWorker worker=new FileWorker();
        List<Work> workList=worker.lehaReader(autoShopConfiguration.getWorkList());

// List<Order> orderList=new ArrayList<>();
// List<Work> workList=new ArrayList<>();
// workList.add(new Work(""))
// Order order=new Order(new Date(),new Date(),new Car("ford","escort","green","1234",1235),"sasha",);
// orderList.add(new Order(new Date(),))

    }
}

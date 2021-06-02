
import facade.AutoShopAdministrator;
import model.entity.car.Car;
import model.entity.master.Master;
import model.entity.order.Order;
import model.entity.work.Work;
import model.enums.Specialty;
import reflection.Reflection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Reflection reflection=new Reflection();
        List<Work> workList=new ArrayList<>();
        workList.add(new Work("change oil",40.5,001));
        workList.add(new Work("change wheels",20.0,002));
        workList.add(new Work("paint",100,003));
        reflection.export(workList);
        List<Master> masterList=new ArrayList<>();
 masterList.add(new Master("Lesha","19.06.1999","+34234234", Specialty.ELECTRIC,101));
 masterList.add(new Master("Maxim","12.02.2005","+123123123", Specialty.DETAILING,402));
        reflection.export(masterList);
        List<Order> orders= new ArrayList<>();
        Order order=new Order(new Date(),new Date(),new Car("ford","escort","green","1234",1235),"sasha",workList,60.5,001);
        order.setMaster(masterList.get(0));
 orders.add(order);

        reflection.export(orders);

    }
}

import menu.MenuController.MenuController;
import model.car.Car;
import model.order.Order;
import utils.FileWorker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class Main {

    public static void main(String[] args) {
        FileWorker worker = new FileWorker();
        Properties properties = worker.getProperties();
        String path = properties.getProperty("orderList");
//        Order order = new Order(new Date(), new Date(), new Car("ford", "escort", "green", "12312"), "sasha", new ArrayList<>(), 234);
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
//        worker.writer(orderList, path);

//        List<Order> orders=  worker.leha(path);
//        System.out.println(orders);
        MenuController menuController = new MenuController();
        menuController.run();


//        List<Work> workList=new ArrayList<>();
//        workList.add(new Work("Remove wheels",50));
//        workList.add(new Work("Change of oil",100));
//        FileWorker worker=new FileWorker();
//        Properties properties=worker.getProperties();
//        String path=properties.getProperty("workList");
//        worker.writer(workList,path);


//        LehaPotok lehaPotok=new LehaPotok();
//        LehaPotok lehaPotok1=new LehaPotok();
//        lehaPotok.start();
//        lehaPotok.join();


//        Class leha= LehaPotok.class;
//        int classModifer=leha.getModifiers();
//        System.out.println(leha.getName());
////        System.out.println(Modifier.isAbstract(classModifer));
////        System.out.println(Modifier.isPublic(classModifer));
//        Class fileWorkerClass=FileWorker.class;
////        System.out.println(fileWorkerClass.getPackageName());
////
//        Annotation[] annotations = leha.getAnnotations();
//        for (Annotation annotation:annotations){
//          Reflectable reflectable=(Reflectable) annotation;
//            System.out.println(reflectable.name());
//        }


    }

}

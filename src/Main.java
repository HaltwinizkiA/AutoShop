
import menu.AdminBuilder;
import menu.MasterBuilder;
import menu.MenuController;
import model.car.Car;
import model.garage.Garage;
import model.order.Order;
import utils.FileWorker;



import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args)  {

//        MenuController menuController = new MenuController();
//        menuController.run();
        FileWorker worker=new FileWorker();
        Car car=new Car("Ford","skatebor","green","kh-2345");
        Order order=new Order(new Date(),new Date(),car,"Sasha","work",2000);
        List<Order> orders=new ArrayList<>();
        orders.add(order);
        worker.writer(orders,"C:\\Users\\37533\\Projects\\AutoShop\\src\\resources\\orderList.ser");



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

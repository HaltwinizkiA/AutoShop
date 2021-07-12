import autoShop.daoImpl.dao.*;
import autoShop.daoImpl.hibernate.HibernateAutoShop;
import autoShop.model.entity.car.Car;
import autoShop.model.entity.garage.Garage;
import autoShop.model.entity.master.Master;
import autoShop.model.entity.order.Order;
import autoShop.model.entity.work.Work;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {



        OrderDao orderDao=new OrderDao();
        List<Order> order=orderDao.getall();
        Order order1=orderDao.getOrderById(1);



        CarDao carDao=new CarDao();
        Car car= carDao.getCarById(2);
        BoxDao boxDao=new BoxDao();
        Garage garage=boxDao.getBoxById(1);
       int a =123;





//
//       List<Work> workList=  workDao.getWorkByIdOrder(1);

//        SessionFactory sessionFactory;
//        Configuration configuration=new Configuration();
//        configuration.configure();
//        ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//        sessionFactory=configuration.buildSessionFactory(serviceRegistry);
//        Work work = new Work("clean", 240.0);
//        Session session=sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(work);
//        session.getTransaction().commit();
//        session.close();


    }
}

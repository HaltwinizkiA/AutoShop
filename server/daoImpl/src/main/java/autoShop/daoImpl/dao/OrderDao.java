package autoShop.daoImpl.dao;


import autoShop.daoImpl.hibernate.HibernateAutoShop;
import autoShop.model.entity.car.Car;
import autoShop.model.entity.order.Order;
import autoShop.model.enums.OrderStatus;
import autoShop.utils.FileWorker;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao extends AbstractDao {
    private static OrderDao orderDao;
    private final FileWorker fileWorker = new FileWorker();
    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    public Order getOrderById(int orderId) {
        try {
        Session session=HibernateAutoShop.getIstance().getSessionFactory().openSession();
        session.getTransaction().begin();
        Order order= (Order) session.get(Order.class,orderId);
        session.getTransaction().commit();
        session.close();
        return order;
    }
        catch (Exception e) {
        fileWorker.logger("master dao err " + e);
    }
        return null;

    }

    public Order addOrder(Date plannedStartDate, Car car, String ownersName) {
        try {
            Order order=new Order(new Date(), plannedStartDate, car, ownersName);
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;
    }

    public Order canceledOrder(int id) {
        try {
            Order order=getOrderById(id);
            order.setStatus(OrderStatus.CANCELED);
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;
    }

    public Order closeOrder(int id) {
        try {
            Order order=getOrderById(id);
            order.setStatus(OrderStatus.CLOSED);
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;

    }

    public Order dellOrder(int id) {
        try {
            Order order=getOrderById(id);
            order.setStatus(OrderStatus.CANCELED);
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(order);
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;
    }

    public Order modifyPlannedStartDate(int id, Date date) {
        try {
            Order order=getOrderById(id);
            order.setPlannedStartDate(date);
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;
    }

    public Order progressOrder(int id) {
        try {
            Order order=getOrderById(id);
            order.setStatus(OrderStatus.IN_PROGRESS);
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;
    }

    public Order save(Order order){
        try {
//            order.setPrice();
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
            session.close();
            return order;
        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;
    }

    public List<Order> sortByComplete() {
        return sort("completion_date");
    }

    public List<Order> sortByCreate() {
        return sort("create_date");
    }

    public List<Order> sortByPlanned() {
        return sort("planned_start_date");
    }

    public List<Order> sortByPRICE() {
        return sort("price");
    }

    public List<Order> getOrdersInProgress() {
        List<Order> orderList = new ArrayList<Order>();
        try {

            Session session=HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query = session.createQuery("FROM User orders where status = 'IN_PROGRESS'");
            orderList=query.list();
            session.close();
            return orderList;

        } catch (Exception e) {
            fileWorker.logger("order dao error " + e);
        }
        return orderList;
    }

    public List<Order> getOrderInTime(Date first, Date second) {
        List<Order> orderList = new ArrayList<Order>();

        try {
            Session session=HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Query query = session.createQuery("FROM orders where create_date>= :first and create_date<= :second");
            query.setParameter("first",first);
            query.setParameter("second",second);
            orderList=query.list();
            session.close();


        } catch (Exception e) {
            fileWorker.logger("order dao error " + e);
        }
        return orderList;
    }

    public Order copyOrderAndModify(Order order, Date plannedStartDate, Car car, String ownersName) {
        if (plannedStartDate != null) {
            order.setPlannedStartDate(plannedStartDate);
        }
        if (car != null) {
            order.setCar(car);
        }
        if (ownersName != null) {
            order.setOwnersName(ownersName);
        }

        addOrder(order.getPlannedStartDate(), order.getCar(), order.getOwnersName());
        return order;

    }

    private List<Order> sort(String sort) {
        List<Order> orderList ;

        try {
            Session session=HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria (Order.class)
                    .addOrder(org.hibernate.criterion.Order.asc(sort));
            orderList=criteria.list();
            return orderList;
        }
        catch (Exception e) {
            fileWorker.logger("order dao error " + e);
        }
        return null;

    }

    public Order transferToMaster(int orderId,int masterId){
        try {
            Order order=getOrderById(orderId);
            order.setMaster(MasterDao.getInstance().getMasterById(masterId));
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.beginTransaction();
            session.update(order);
            session.close();
            return order;

        } catch (Exception e) {
            fileWorker.logger("order dao err " + e);
        }
        return null;

    }

    @Override
    public List getall() {
        List<Order> orderList ;

        try {
            Session session= HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria=session.createCriteria(Order.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            orderList=(List<Order>) criteria.list();

            session.close();
            return orderList;
        } catch (Exception e) {
            fileWorker.logger("order dao error " + e);
        }


        return null;
    }
}

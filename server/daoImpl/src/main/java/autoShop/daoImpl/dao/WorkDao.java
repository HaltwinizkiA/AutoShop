package autoShop.daoImpl.dao;

import autoShop.daoImpl.hibernate.HibernateAutoShop;
import autoShop.model.entity.order.Order;
import autoShop.model.entity.work.Work;
import autoShop.utils.FileWorker;
import org.hibernate.Criteria;
import org.hibernate.Session;


import java.util.ArrayList;
import java.util.List;

public class WorkDao extends AbstractDao {
    private static WorkDao workDao;
    private final FileWorker fileWorker = new FileWorker();


    public static WorkDao getInstance() {
        if (workDao == null) {
            workDao = new WorkDao();
        }
        return workDao;
    }

    public Work addWork(String name, String price) {

        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            Work work = new Work(name, Double.parseDouble(price));
            session.beginTransaction();
            session.save(work);
            session.getTransaction().commit();
            session.close();
            return work;
        } catch (Exception e) {
            fileWorker.logger("work dao err " + e);
        }

        return null;
    }

    public void deleteWork(String id) {
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(getWorkById(Integer.parseInt(id)));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            fileWorker.logger("work dao err " + e);
        }

    }

    public Work getWorkById(int id) {
        Work work;
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            work = (Work) session.get(Work.class, id);
            session.getTransaction().commit();
            session.close();
            return work;

        } catch (Exception e) {
            fileWorker.logger("work dao err " + e);
        }
        return null;
    }

    public String setWorkToOrder(int orderId, int workID) {
        Order order = OrderDao.getInstance().getOrderById(orderId);
        OrderDao.getInstance().save(order);
        return "successful";
    }

    @Override
    public List getall() {
        List<Work> workList = new ArrayList<Work>();
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Work.class);
            workList = criteria.list();
            session.close();

        } catch (Exception e) {
            fileWorker.logger(e.toString());
        }
        return workList;
    }
}

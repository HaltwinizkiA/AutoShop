package autoShop.daoImpl.dao;


import autoShop.daoImpl.hibernate.HibernateAutoShop;
import autoShop.model.entity.master.Master;
import autoShop.model.enums.Specialty;
import autoShop.utils.FileWorker;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;


import java.util.ArrayList;
import java.util.List;

public class MasterDao extends AbstractDao {
    private static MasterDao masterDao;
    private final FileWorker fileWorker = new FileWorker();

    public static MasterDao getInstance() {

        if (masterDao == null) {
            masterDao = new MasterDao();
        }
        return masterDao;
    }

    public Master addMaster(String name, String dateOfbirth, String specialty, String phoneNumber) {

        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            Master master = new Master(name, dateOfbirth, phoneNumber, Specialty.valueOf(specialty));
            session.beginTransaction();
            session.save(master);
            session.getTransaction().commit();
            session.close();
            return master;

        } catch (Exception e) {
            fileWorker.logger("master dao err " + e);
        }

        return null;
    }

    public Master dellMaster(int id) {
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(getMasterById(id));
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            fileWorker.logger("master dao err " + e);
        }
        return null;

    }

    public List sortBusyMaster() {
        List<Master> masterList = new ArrayList<Master>();
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Master.class)
                    .addOrder(Order.asc("status"));
            masterList = criteria.list();
            return masterList;
        } catch (Exception e) {
            fileWorker.logger("master dao error " + e);
        }
        return null;

    }

    public List sortNameMaster() {
        List<Master> masterList = new ArrayList<Master>();
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Master.class)
                    .addOrder(Order.asc("name"));
            masterList = criteria.list();
            return masterList;
        } catch (Exception e) {
            fileWorker.logger("master dao error " + e);
        }
        return null;
    }

    public Master getMasterById(int id) {
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Master master = (Master) session.get(Master.class, id);
            session.getTransaction().commit();
            session.close();
            return master;
        } catch (Exception e) {
            fileWorker.logger("master dao err " + e);
        }
        return null;
    }

    @Override
    public List getall() {
        List<Master> masterList = new ArrayList<Master>();

        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Master.class);
            masterList = criteria.list();
            session.close();
        } catch (Exception e) {
            fileWorker.logger("master dao err " + e);
        }
        return masterList;
    }
}

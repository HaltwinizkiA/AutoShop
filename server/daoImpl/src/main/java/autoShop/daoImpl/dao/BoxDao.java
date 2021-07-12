package autoShop.daoImpl.dao;

import autoShop.daoImpl.hibernate.HibernateAutoShop;
import autoShop.model.entity.car.Car;
import autoShop.model.entity.garage.Garage;
import autoShop.utils.FileWorker;
import org.hibernate.Criteria;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class BoxDao extends AbstractDao {
    private static BoxDao boxDao;
    FileWorker fileWorker = new FileWorker();

    public static BoxDao getInstance() {
        if (boxDao == null) {
            boxDao = new BoxDao();
        }
        return boxDao;
    }

    @Override
    public List getall() {
        List<Garage> boxList = new ArrayList<Garage>();
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Garage.class);
            boxList = criteria.list();
            session.close();
            return boxList;

        } catch (Exception e) {
            fileWorker.logger(e.toString());
        }

        return null;
    }

    public Garage getBoxById(int boxId) {
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Garage box = (Garage) session.get(Garage.class, boxId);
            session.getTransaction().commit();
            session.close();
            return box;
        } catch (Exception e) {
            fileWorker.logger("box dao ex " + e);
        }
        return null;
    }

    public Garage modifyBoxCapacity(int boxId, int newCapacity) {
        try {
            Garage box = getBoxById(boxId);
            box.setCapacity(newCapacity);
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(box);
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            fileWorker.logger("box dao err " + e);
        }

        return null;

    }

    public Garage save(Garage box) {
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(box);
            session.getTransaction().commit();
            session.close();
            return box;
        } catch (Exception e) {
            fileWorker.logger("box dao ex " + e);
        }
        return null;
    }

    public List<Car> viewCarinBox(int boxId) {
        return getBoxById(boxId).getCarList();


    }

    public int viewFreeBox(int boxId) {
        Garage garage = getBoxById(boxId);
        return garage.getCapacity() - garage.getCarList().size();
    }

    public String freeUpSpaceBox(int carId) {
        Car car = CarDao.getInstance().getCarById(carId);
        List<Garage> boxs = getall();
        for (Garage garage : boxs) {
            garage.getCarList().remove(car);
        }
        return "unsuccessful";
    }

    public boolean checkFreePlace(int boxId) {
        Garage garage = getBoxById(boxId);
        return garage.getCapacity() < garage.getCarList().size();
    }
}

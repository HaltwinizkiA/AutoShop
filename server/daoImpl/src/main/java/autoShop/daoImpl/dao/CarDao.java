package autoShop.daoImpl.dao;


import autoShop.daoImpl.hibernate.HibernateAutoShop;
import autoShop.model.entity.car.Car;
import autoShop.model.entity.garage.Garage;
import autoShop.utils.FileWorker;
import org.hibernate.Criteria;
import org.hibernate.Session;


import java.util.ArrayList;

import java.util.List;

public class CarDao extends AbstractDao {
    private static CarDao carDao;
    private final FileWorker fileWorker = new FileWorker();

    private final String BOX_CARS = "insert into `autoshop`.`box_cars`(box_id,car_id) values(?,?)";


    public static CarDao getInstance() {
        if (carDao == null) {
            carDao = new CarDao();
        }
        return carDao;
    }

    public String setCarInBox(int carId, int boxId) {

        if (BoxDao.getInstance().checkFreePlace(boxId)) {
            Garage garage = BoxDao.getInstance().getBoxById(boxId);
            garage.getCarList().add(getCarById(carId));
            BoxDao.getInstance().save(garage);
        }
        return "unsuccessfully";

    }


    public Car addCar(String mark, String model, String color, String number) {

        try {

            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            Car car = new Car(mark, model, color, number);
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
            session.close();
            return car;

        } catch (Exception e) {
            fileWorker.logger("car dao err " + e);
        }
        return null;


    }

    public Car getCarById(int id) {
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Car car = (Car) session.get(Car.class, id);
            session.getTransaction().commit();
            session.close();
            return car;
        } catch (Exception e) {
            fileWorker.logger("car dao ex " + e);
        }
        return null;
    }

    @Override
    public List getall() {
        List<Car> carList = new ArrayList<Car>();
        try {
            Session session = HibernateAutoShop.getIstance().getSessionFactory().openSession();
            session.getTransaction().begin();
            Criteria criteria = session.createCriteria(Car.class);
            carList = criteria.list();
            session.close();
            return carList;
        } catch (Exception e) {
            fileWorker.logger("car dao error " + e);
        }
        return carList;
    }
}

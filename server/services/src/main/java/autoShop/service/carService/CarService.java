package autoShop.service.carService;


import autoShop.daoImpl.dao.CarDao;

public class CarService {
    private final CarDao carDao;
    public CarService(){
        this.carDao=new CarDao();
    }

    public synchronized String transferCarInBox(String carId, String boxId) {

        return carDao.setCarInBox(Integer.parseInt(carId),Integer.parseInt(boxId));
    }
}
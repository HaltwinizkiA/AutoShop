package autoShop.service.workService;


import autoShop.daoImpl.dao.WorkDao;
import autoShop.service.AService;

public class WorkService extends AService {
    private final WorkDao workDao;

    public WorkService(){
        this.workDao=new WorkDao();
    }


    public synchronized String addWorkToOrder(String orderId,String workId) {
        workDao.setWorkToOrder(Integer.parseInt(orderId),Integer.parseInt(workId));

        return "work added";
    }

    public synchronized String addWork(String workingName, String price) {
        workDao.addWork(workingName,price);
        return "successfully";

    }//todo action

    public synchronized String dellWork(String id) {
        workDao.deleteWork(id);
        return "successfully";
    }

    public String viewWorkList() {

        return view(workDao.getall());
    }

}

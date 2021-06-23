import dao.MasterDao;
import model.entity.master.Master;

public class MasterService {
    private final MasterDao masterDao;
    public MasterService( ){
        this.masterDao=new MasterDao();
    }

    public synchronized String addMaster(String name, String dateOfBirth, String phoneNumber, String specialty) {

        return "added master :" + masterDao.addMaster(name, dateOfBirth, specialty, phoneNumber);
    }

    public synchronized String dellMaster(String id) {

        return "master deleted " + masterDao.dellMaster(Integer.parseInt(id)).toString();

    }
    public synchronized String masterBusySort() {
        return masterDao.view(masterDao.sortBusyMaster());
    }
    public synchronized String masterNameSort() {

        return masterDao.view(masterDao.sortNameMaster());
    }
    public synchronized String viewAllMaster() {
        return masterDao.view(masterDao.getall());
    }
    public String viewMasterInOrder(String orderId) {
        return masterDao.getMasterInOrder(Integer.parseInt(orderId)).toString();
    }
}

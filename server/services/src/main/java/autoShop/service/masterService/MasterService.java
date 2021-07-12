package autoShop.service.masterService;


import autoShop.daoImpl.dao.MasterDao;
import autoShop.model.entity.master.Master;
import autoShop.service.AService;

import java.util.List;

public class MasterService extends AService {
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
        return view(masterDao.sortBusyMaster());
    }
    public synchronized String masterNameSort() {

        return view(masterDao.sortNameMaster());
    }
    public synchronized String viewAllMaster() {
        return view(masterDao.getall());
    }

    public String viewMasterInOrder(String orderId) {
        List<Master> masterList=masterDao.getall();
        for (Master master:masterList){
            if (master.getOrder().getId()==Integer.parseInt(orderId)){
                return master.toString();
            }
        }
        return null;
    }
}

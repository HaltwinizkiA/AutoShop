import dao.WorkDao;

public class WorkService {
    private final WorkDao workDao;

    public WorkService(){
        this.workDao=new WorkDao();
    }


    public synchronized String addWorkToOrder(String orderId,String... numbers) {
        workDao.setWorkToOrder(Integer.parseInt(orderId),numbers);

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

        return workDao.view(workDao.getall());
    }

}

import dao.CarDao;
import dao.OrderDao;
import utils.FileWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderService {
    private final OrderDao orderDao;
    private final FileWorker fileWorker;

    public OrderService() {
        this.orderDao = new OrderDao();
        this.fileWorker = new FileWorker();


    }

    public synchronized String addOrder(String plannedStartDate, String carId, String owner) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date formatPlannedStartDate = null;
        try {
            formatPlannedStartDate = dateFormat.parse(plannedStartDate);

        } catch (ParseException e) {
            fileWorker.logger("add order exception" + e);
        }
        return "order added: " + orderDao.addOrder(formatPlannedStartDate, CarDao.getInstance().getCarById(Integer.parseInt(carId)), owner);

    }

    public synchronized String canceledOrder(String num) {

        return "order canceled" + orderDao.canceledOrder(Integer.parseInt(num)).toString();
    }

    public synchronized String closedOrder(String num) {

        return "order closed" + orderDao.closeOrder(Integer.parseInt(num)).toString();
    }

    public synchronized void copyOrderAndModify(String orderId, String carId, String ownersName, String plannedStartDate) {


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date formatPlannedStartDate = null;
        try {
            formatPlannedStartDate = dateFormat.parse(plannedStartDate);

        } catch (ParseException e) {
            fileWorker.logger("add order exception" + e);
        }
        orderDao.copyOrderAndModify(orderDao.getOrderById(Integer.parseInt(orderId)), formatPlannedStartDate, CarDao.getInstance().getCarById(Integer.parseInt(carId)), ownersName);


    }

    public synchronized String dellOrder(String id) {
        orderDao.dellOrder(Integer.parseInt(id));
        return "order deleted";
    }

    public synchronized String modifyPlannedStartDate(String id, String date) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date formDate = null;
        try {
            formDate = dateFormat.parse(date);

        } catch (ParseException e) {
            fileWorker.logger("add order exception" + e);
        }
        orderDao.modifyPlannedStartDate(Integer.parseInt(id), formDate);

        return "order changed";
    }

    public synchronized String progressOrder(String id) {
        orderDao.progressOrder(Integer.parseInt(id));
        return "order changed";
    }

    public synchronized String ordersSortDateComplete() {

        return orderDao.view(orderDao.sortByComplete());
    }

    public synchronized String ordersSortDateCreate() {

        return orderDao.view(orderDao.sortByCreate());
    }

    public synchronized String ordersSortPlannedStartDate() {

        return orderDao.view(orderDao.sortByPlanned());
    }

    public synchronized String ordersSortPrice() {

        return orderDao.view(orderDao.sortByPRICE());
    }

    public synchronized String orderTransferToTheMaster(String orderId, String masterID) {

        return orderDao.transferToMaster(Integer.parseInt(orderId), Integer.parseInt(masterID)).toString();
    }

    public String viewAllOrder() {

        return orderDao.view(orderDao.getOrderList());
    }

    public String viewOrderInProgress() {

        return orderDao.view(orderDao.getOrdersInProgress());
    }

    public String viewOrderInTime(String firstTime, String secondTime) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date formatFirst = null;
        Date formatSecond = null;
        try {
            formatFirst = dateFormat.parse(firstTime);
            formatSecond = dateFormat.parse(secondTime);


        } catch (ParseException e) {
            fileWorker.logger("add order exception" + e);
        }

        return orderDao.view(orderDao.getOrderInTime(formatFirst, formatSecond));
    }


}

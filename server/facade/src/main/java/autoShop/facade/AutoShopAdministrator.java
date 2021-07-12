package autoShop.facade;


import autoShop.service.boxService.BoxService;
import autoShop.service.carService.CarService;
import autoShop.service.masterService.MasterService;
import autoShop.service.orderService.OrderService;
import autoShop.service.workService.WorkService;

public class AutoShopAdministrator {
    private static AutoShopAdministrator autoShopAdministrator;
     private final OrderService orderService;
    private final MasterService masterService;
    private final WorkService workService;
    private final CarService carService;
    private final BoxService boxService;


    private AutoShopAdministrator() {
        this.orderService = new OrderService();
        this.masterService = new MasterService();
        this.workService = new WorkService();
        this.carService = new CarService();
        this.boxService = new BoxService();
    }

    public static AutoShopAdministrator getInstance() {
        if (autoShopAdministrator == null) {
            autoShopAdministrator = new AutoShopAdministrator();
        }
        return autoShopAdministrator;
    }


    public synchronized String addMaster(String name, String dateOfBirth, String phoneNumber, String specialty) {

        return masterService.addMaster(name,dateOfBirth,phoneNumber,specialty);
    }

    public synchronized String transferCarInBox(String carId, String boxId) {

        return carService.transferCarInBox(carId,boxId);
    }

    public synchronized String dellMaster(String id) {

        return masterService.dellMaster(id);

    }

    public synchronized String masterBusySort() {

        return masterService.masterBusySort();
    }

    public synchronized String masterNameSort() {

        return masterService.masterNameSort();
    }

    public synchronized String viewAllMaster() {

        return masterService.viewAllMaster();
    }

    public String viewMasterInOrder(String orderId) {
        return masterService.viewMasterInOrder(orderId);
    }

    public synchronized String addOrder(String plannedStartDate,String carId, String owner) {

        return orderService.addOrder(plannedStartDate,carId,owner);

    }

    public synchronized String addWorkToOrder(String orderId,String workId) {

        return workService.addWorkToOrder(orderId,workId);
    }

    public synchronized String canceledOrder(String id) {
        return orderService.canceledOrder(id);
    }

    public synchronized String closedOrder(String id) {
        return orderService.closedOrder(id);
    }

    public synchronized void copyOrderAndModify(String orderId, String carId, String ownersName,String plannedStartDate) {
        orderService.copyOrderAndModify(orderId,carId,ownersName,plannedStartDate);

    }

    public synchronized String dellOrder(String id) {
        return orderService.dellOrder(id);
    }

    public synchronized String modifyPlannedStartDate(String id, String date) {
        return orderService.modifyPlannedStartDate(id,date);
    }

    public synchronized String ordersSortDateComplete() {
        return orderService.ordersSortDateComplete();
    }

    public synchronized String ordersSortDateCreate() {
        return orderService.ordersSortDateCreate();
    }

    public synchronized String ordersSortPlannedStartDate() {
       return orderService.ordersSortPlannedStartDate();
    }

    public synchronized String ordersSortPrice() {
     return orderService.ordersSortPrice();
    }

    public synchronized String orderTransferToTheMaster(String orderId, String masterId) {
        return orderService.orderTransferToTheMaster(orderId,masterId);
    }

    public String viewAllOrder() {
        return orderService.viewAllOrder();
    }

    public String viewOrderInProgress() {
        return orderService.viewOrderInProgress();
    }

    public String viewOrderInTime(String firstTime, String secondTime) {

        return orderService.viewOrderInTime(firstTime,secondTime);
    }

    public synchronized String freeUpSpaceBox(String id) {
       return boxService.freeUpSpaceBox(id);
    }

    public synchronized String modifyBoxCapacity(String boxId, String newCapacity) {
       return boxService.modifyBoxCapacity(boxId,newCapacity);
    }

    public String viewFreeBox(String boxID) {
        return boxService.viewFreeBox(boxID);

    }

    public synchronized String addWork(String workingName, String price) {
       return workService.addWork(workingName,price);

    }

    public synchronized String dellWork(String id) {
        return workService.dellWork(id);
    }

    public String viewWorkList() {
       return workService.viewWorkList();
    }

    public String viewAllCarInBox(String boxID) {
        return boxService.viewAllCarInBox(boxID);
    }

}

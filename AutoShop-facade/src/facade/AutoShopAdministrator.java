package facade;


import configuration.AutoShopConfiguration;
import model.comporators.master.MasterComparator;
import model.comporators.order.OrderComparator;
import model.entity.car.Car;
import model.entity.garage.Garage;
import model.entity.master.Master;
import model.entity.order.Order;
import model.entity.work.Work;
import model.enums.OrderStatus;
import model.enums.Specialty;
import utils.FileWorker;
import utils.TextWorker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AutoShopAdministrator {
    private static AutoShopAdministrator autoShopAdministrator;
    private static AutoShopConfiguration autoShopConfiguration;
    private final TextWorker textWorker;
    private final FileWorker fileWorker;
    private final List<Order> orderList;
    private final List<Garage> boxList;
    private final List<Work> workList;
    private volatile List<Master> masterList;
    private volatile Order orderCash;

    private AutoShopAdministrator() {
        orderCash = null;
        textWorker = new TextWorker();
        fileWorker = new FileWorker();
        autoShopConfiguration = new AutoShopConfiguration();
        orderList = fileWorker.lehaReader(autoShopConfiguration.getOrderListPath());
        boxList = fileWorker.lehaReader(autoShopConfiguration.getBoxListPath());
        masterList = fileWorker.lehaReader(autoShopConfiguration.getMasterListPath());
        workList = fileWorker.lehaReader(autoShopConfiguration.getWorkListPath());

    }

    public static AutoShopAdministrator getInstance() {
        if (autoShopAdministrator == null) {
            autoShopAdministrator = new AutoShopAdministrator();
        }
        return autoShopAdministrator;
    }

    public synchronized void csvMasterListWrite() {
        fileWorker.csvMasterWriter(autoShopConfiguration.getMasterCSVPath(), masterList);
        textWorker.println("master list csv  saved ");
    }

    public synchronized String save() {
        saveWorkList(autoShopConfiguration.getWorkListPath());
        saveBoxList(autoShopConfiguration.getBoxListPath());
        saveMasterList(autoShopConfiguration.getMasterListPath());
        saveOrderList(autoShopConfiguration.getOrderListPath());
        return "database saved";
    }

    public void csvMasterListRead() {
        masterList = fileWorker.csvMasterReader(autoShopConfiguration.getMasterCSVPath());
        viewAllMaster();
    }

    public synchronized void csvOrderListWrite() {
        fileWorker.csvOrderWriter(autoShopConfiguration.getMasterCSVPath(), orderList);
        textWorker.println("order list csv  saved ");
    }

    public void csvOrderListRead() {
        fileWorker.csvOrderReader(autoShopConfiguration.getOrderCSVPath(), autoShopConfiguration.getWorkCSVPath(), autoShopConfiguration.getMasterCSVPath());
        viewAllOrder();

    }

    public synchronized void csvWorkWrite() {
        fileWorker.csvWorkWriter(autoShopConfiguration.getWorkListPath(), workList);
        textWorker.println("work list csv  saved ");
    }

    public void csvWorkRead() {
        fileWorker.csvWorkReader(autoShopConfiguration.getWorkListPath());
        viewWorkList();
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public synchronized void saveOrderList(String path) {
        fileWorker.writer(orderList, path);
    }

    public synchronized void saveBoxList(String path) {
        fileWorker.writer(boxList, path);
    }

    public synchronized void saveMasterList(String path) {
        fileWorker.writer(masterList, path);
    }

    public synchronized void saveWorkList(String path) {
        fileWorker.writer(workList, path);
    }

    public synchronized String addMaster(String name, String dateOfBirth, String phoneNumber, String specialty, String id) {
        Master master = new Master(name, dateOfBirth, phoneNumber, Specialty.valueOf(specialty), Integer.parseInt(id));
        masterList.add(master);
        return "added master :" + master.toString();
    }

    public synchronized String transferCarInBox(String orderNum, String boxNum) {
        boxList.get(Integer.parseInt(boxNum)).getCarsList().add(orderList.get(Integer.parseInt(orderNum)).getCar());
        return "car : " + orderList.get(Integer.parseInt(orderNum)).getCar() + " transferred";
    }

    public synchronized String dellMaster(String numOfDell) {
        masterList.remove(Integer.parseInt(numOfDell));
        return "master deleted ";

    }

    public synchronized String masterBusySort() {
        Collections.sort(masterList, new MasterComparator().getMasterBusySort());
        return viewAllMaster();
    }

    public synchronized String masterNameSort() {
        Collections.sort(masterList, new MasterComparator().getMasterNameSort());
        return viewAllMaster();
    }

    public synchronized String viewAllMaster() {
        String line = "";
        for (int i = 0; i < masterList.size(); i++) {
            line = line + "\n" + i + " " + masterList.get(i);
        }
        return line;
    }

    public String viewMasterInOrder(String orderNum) {
        return orderList.get(Integer.parseInt(orderNum)).getMaster().toString();
    }

    public synchronized String addOrder(String plannedStartDate, String mark, String model, String color, String number, String carId, String owner, String id) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date formatPlannedStartDate = null;
        try {
            formatPlannedStartDate = dateFormat.parse(plannedStartDate);

        } catch (ParseException e) {
            fileWorker.logger("add order exception" + e);
        }

        orderCash = new Order(new Date(), formatPlannedStartDate, new Car(mark, model, color, number, Integer.parseInt(carId)), owner, Integer.parseInt(id));
        orderList.add(new Order(new Date(), formatPlannedStartDate, new Car(mark, model, color, number, Integer.parseInt(carId)), owner, Integer.parseInt(id)));
        return "order added";

    }

    public synchronized String addWorkToOrder(String... numbers) {
        List<Work> works = new ArrayList<>();
        Double price = 0.0;
        for (String num : numbers) {
            price += workList.get(Integer.parseInt(num)).getPrice();
            works.add(workList.get(Integer.parseInt(num)));
        }
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == orderCash.getId()) {
                orderList.get(i).setPrice(price);
                orderList.get(i).setWork(works);
            }
        }

        return "work added";
    }

    public synchronized String canceledOrder(String num) {
        orderList.get(Integer.parseInt(num)).setStatus(OrderStatus.CANCELED);
        return "order canceled";
    }

    public synchronized String closedOrder(String num) {
        orderList.get(Integer.parseInt(num)).setStatus(OrderStatus.CLOSED);
        return "order closed";
    }

    public synchronized void copyOrderAndModify(String numOfCopyOrder,  String mark, String model, String color, String number, String carId, String ownersName, String masterName,String plannedStartDate,  String id) {
        try {
            Order order = orderList.get(Integer.parseInt(numOfCopyOrder)).clone();

            for (Master master:masterList){
                if (master.getName().equals(masterName)){
                    order.setMaster(master);
                };
            }

            if (!mark.equals("")) {
                order.setCar(new Car(mark,model,color,number,Integer.parseInt(carId)));
            }
            if (!ownersName.equals("")) {
                order.setOwnersName(ownersName);
            }

            if (plannedStartDate.equals("")) {
                DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
                dateFormat.setLenient(false);
                Date formatPlannedStartDate = null;
                try {
                    formatPlannedStartDate = dateFormat.parse(plannedStartDate);

                } catch (ParseException e) {
                    fileWorker.logger("add order exception" + e);
                }
                order.setPlannedStartDate(formatPlannedStartDate);
            }
            order.setId(Integer.parseInt(id));
            orderList.add(order);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    public synchronized String dellOrder(String num) {
        orderList.remove(Integer.parseInt(num));
        return "order deleted";
    }

    public synchronized String modifyPlannedStartDate(String num, String date) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yy");
        dateFormat.setLenient(false);
        Date formDate = null;
        try {
            formDate = dateFormat.parse(date);

        } catch (ParseException e) {
            fileWorker.logger("add order exception" + e);
        }
        orderList.get(Integer.parseInt(num)).setPlannedStartDate(formDate);
        return "order changed";
    }

    public synchronized String progressOrder(String num) {
        orderList.get(Integer.parseInt(num)).setStatus(OrderStatus.IN_PROGRESS);
        return "order changed";
    }

    public synchronized String ordersSortDateComplete() {
        Collections.sort(orderList, new OrderComparator().getOrderDateCompliteComparator());
        return viewAllOrder();
    }

    public synchronized String ordersSortDateCreate() {
        Collections.sort(orderList, new OrderComparator().getOrderDateComparator());
        return viewAllOrder();
    }

    public synchronized String ordersSortPlannedStartDate() {
        Collections.sort(orderList, new OrderComparator().getOrderPlannedStartDate());
        return viewAllOrder();
    }

    public synchronized String ordersSortPrice() {
        Collections.sort(orderList, new OrderComparator().getOrderPriceComparator());
        return viewAllOrder();
    }

    public synchronized String orderTransferToTheMaster(String orderNum, String masterNum) {
        orderList.get(Integer.parseInt(orderNum)).setMaster(masterList.get(Integer.parseInt(masterNum)));
        return viewAllOrder();
    }

    public String viewAllOrder() {
        String line = "";
        for (int i = 0; i < orderList.size(); i++) {
            line = line + "\n" + i + " " + orderList.get(i);
        }
        return line;
    }

    public String viewOrderInProgress() {
        for (Order order : orderList) {
            if (order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
                return order.toString();
            }
        }
        return "not orders in progress";
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

        for (Order order : orderList) {
            if (order.getCreateOrderDate().getTime() >= formatFirst.getTime() & order.getCreateOrderDate().getTime() <= formatSecond.getTime()) {
                return order.toString();
            }
        }
        return "not orders in this time";
    }

    public synchronized String freeUpSpaceBox(String boxNum, String carNum) {
        boxList.get(Integer.parseInt(boxNum)).getCarsList().remove(Integer.parseInt(carNum));
        return "successfully";
    }

    public synchronized String modifyBoxCapacity(String boxNum, String newCapacity) {
        boxList.get(Integer.parseInt(boxNum)).setCapacity(Integer.parseInt(newCapacity));
        return "successfully";
    }

    public String viewFreeBox(String boxNum) {
        int capacity = boxList.get(Integer.parseInt(boxNum)).getCapacity();
        int takenPlace = boxList.size();
        return takenPlace + " / " + capacity;

    }

    public synchronized String addWork(String workingName, String price, String id) {
        Work work = new Work(workingName, Double.parseDouble(price), Integer.parseInt(id));
        workList.add(work);
        return "successfully";

    }

    public synchronized String dellWork(String num) {
        workList.remove(Integer.parseInt(num));
        return "successfully";
    }

    public String viewWorkList() {
        String line = "";
        for (int i = 0; i < workList.size(); i++) {
            line = line + "\n" + i + " " + workList.get(i);
        }
        return line;
    }

    public String viewAllCarInBox() {
        String line = "";
        line = "Box count:" + boxList.size();
        for (int i = 0; i < boxList.size(); i++) {
            line = line + "\n" + "Box num:" + i;
            for (int car = 0; car < boxList.get(i).getCarsList().size(); car++) {
                line = line + "\n" + car + " " + boxList.get(i).getCarsList().get(car).toString();
            }
        }
        return line;
    }

}

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
    private final List<Master> masterList;
    private final List<Work> workList;

    private AutoShopAdministrator() {
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

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public void saveOrderList(String path) {
        fileWorker.writer(orderList, path);
    }

    public void saveBoxList(String path) {
        fileWorker.writer(boxList, path);
    }

    public void saveMasterList(String path) {
        fileWorker.writer(masterList, path);
    }

    public void saveWorkList(String path) {
        fileWorker.writer(workList, path);
    }

    public void addMaster(String name, String dateObrith, String phoneNumber, Specialty specialty,Integer id) {
        masterList.add(new Master(name, dateObrith, phoneNumber, specialty,id));

    }

    public void dellMaster(int numOfDell) {
        masterList.remove(numOfDell);

    }

    public void masterBusySort() {
        Collections.sort(masterList, new MasterComparator().getMasterBusySort());
    }

    public void masterNameSort() {
        Collections.sort(masterList, new MasterComparator().getMasterNameSort());
    }

    public void viewAllMaster() {
        for (int i = 0; i < masterList.size(); i++) {
            textWorker.println(i + " " + masterList.get(i));
        }
    }

    public void viewMasterInOrder(int orderNum) {

        for (Master master : masterList) {
            if (master.getOrder().equals(orderList.get(orderNum))) {
                textWorker.println(master.toString());
            }
        }
    }

    public void addOrder(Date plannedStartDate, Car car, String owner, Integer id) {
        viewWorkList();
        List<Work> works = new ArrayList<>();
        double price = 0;
        while (true) {
            textWorker.println("enter work num 0-" + workList.size() + "\n -stop for stop");
            try {
                int num = textWorker.getIntInput();
                works.add(workList.get(num));
                price += workList.get(num).getPrice();
            } catch (Exception e) {
                break;
            }

        }


        orderList.add(new Order(new Date(), plannedStartDate, car, owner, works, price,id));
    }

    public void canceledOrder(int num) {
        orderList.get(num).setStatus(OrderStatus.CANCELED);
    }

    public void closedOrder(int num) {
        orderList.get(num).setStatus(OrderStatus.CLOSED);
    }

    //TODO
    public void copyOrderAndModify(int numOfCopyOrder, Car car, String ownersName, List<Work> work, String masterName, Integer numOfBox, OrderStatus status, Date plannedStartDate,Double price,Integer id) {
        try {
            Order order = orderList.get(numOfCopyOrder).clone();
            if (car != null) {
                order.setCar(car);
            }
            if (ownersName != null) {
                order.setOwnersName(ownersName);
            }
            if (work != null) {
                order.setWork(work);
            }
            for (Master master : masterList) {
                if (masterName.equals(master.getName())) {
                    order.setMaster(master);
                }
            }
            if (numOfBox != null) {
                order.setGarage(boxList.get(numOfBox));
            }
            if (status != null) {
                order.setStatus(status);
            }
            if (plannedStartDate != null) {
                order.setPlannedStartDate(plannedStartDate);
            }
            if (price!=null){
                order.setPrice(price);
            }
            order.setId(id);
            orderList.add(order);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }//TODO//todo

    public void dellOrder(int num) {
        orderList.remove(num);
    }

    public void modifyPlannedStartDate(int num, Date date) {
        orderList.get(num).setPlannedStartDate(date);
    }

    public void progressOrder(int num) {
        orderList.get(num).setStatus(OrderStatus.IN_PROGRESS);
    }

    public void ordersSortDateComplete() {
        Collections.sort(orderList, new OrderComparator().getOrderDateCompliteComparator());
    }

    public void ordersSortDateCreate() {
        Collections.sort(orderList, new OrderComparator().getOrderDateComparator());
    }

    public void ordersSortPlannedStartDate() {
        Collections.sort(orderList, new OrderComparator().getOrderPlannedStartDate());
    }

    public void ordersSortPrice() {
        Collections.sort(orderList, new OrderComparator().getOrderPriceComparator());
    }

    public void orderTransferToTheMaster(int orderNum, int masterNum) {
        masterList.get(masterNum).setOrder(orderList.get(orderNum));
    }

    public void viewAllOrder() {
        for (int i = 0; i < orderList.size(); i++) {
            textWorker.println(i + " " + orderList.get(i));
        }
    }

    public void viewOrderInProgress() {
        for (Order order : orderList) {
            if (order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
                textWorker.println(order.toString());
            }
        }
    }

    public void viewOrderInTime(Date firstTime, Date secondTime) {
        for (Order order : orderList) {
            if (order.getCreateOrderDate().getTime() >= firstTime.getTime() & order.getCreateOrderDate().getTime() <= secondTime.getTime()) {
                textWorker.println(order.toString());
            }
        }
    }

    public void freeUpSpaceBox(int boxNum, int carNum) {
        boxList.get(boxNum).getCarsList().remove(carNum);
    }

    public void modifyBoxCapacity(int boxNum, int newCapacity) {
        boxList.get(boxNum).setCapacity(newCapacity);
    }

    public void viewFreeBox(int boxNum) {
        int capacity = boxList.get(boxNum).getCapacity();
        int takenPlace = boxList.size();
        textWorker.println(takenPlace + " / " + capacity);

    }

    public void addWork(String workingName, double price,Integer id) {
        Work work = new Work(workingName, price,id);
        workList.add(work);

    }

    public void dellWork(int num) {
        workList.remove(num);
    }

    public void viewWorkList() {
        for (int i = 0; i < workList.size(); i++) {
            textWorker.println(i + " " + workList.get(i));
        }
    }

    public void viewAllCarInBox() {
        textWorker.println("Box count:" + boxList.size());
        for (int i = 0; i < boxList.size(); i++) {
            textWorker.println("Box num:" + i);
            for (int car = 0; car < boxList.get(i).getCarsList().size(); car++) {
                textWorker.println(car + " " + boxList.get(i).getCarsList().get(car).toString());
            }
        }
    }

}

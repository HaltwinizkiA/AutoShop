package model.entity.order;



import model.entity.car.Car;
import model.enums.OrderStatus;
import model.entity.garage.Garage;
import model.entity.master.Master;
import model.entity.work.Work;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable, Cloneable {
    private  Date createOrderDate;
    private  Car car;
    private  String ownersName;
    private  List<Work> work;
    private  double price;
    private Date dateOfCompletion;
    private Date plannedStartDate;
    private OrderStatus status;
    private Master master;
    private Garage garage;
    private Integer id;

    public Master getMaster() {
        return master;
    }

    public Garage getGarage() {
        return garage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreateOrderDate(Date createOrderDate) {
        this.createOrderDate = createOrderDate;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName, List<Work> work, double price,Integer id) {
        this.createOrderDate = createOrderDate;
        this.id=id;
        this.plannedStartDate = plannedStartDate;
        this.car = car;
        this.ownersName = ownersName;
        this.work = work;
        this.price = price;
        status = OrderStatus.ACCEPTED;

    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public Date getPlannedStartDate() {
        return plannedStartDate;
    }

    public void setPlannedStartDate(Date plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Car getCar() {
        return car;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public List<Work> getWork() {
        return work;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;

    }

    public Date getCreateOrderDate() {
        return createOrderDate;
    }


    public double getPrice() {
        return price;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    @Override
    public String toString() {
        return "Order status: " + status + "/ car arrival time: " + createOrderDate + "/planned start date:" + plannedStartDate + "/ ownersName:" + ownersName + "/ work:" + work + "/ " + car
                + " \nprice: " + price;
    }
}

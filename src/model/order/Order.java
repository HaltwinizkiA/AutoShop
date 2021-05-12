package model.order;


import annotation.OrderStatus;
import model.car.Car;
import model.garage.Garage;
import model.master.Master;
import model.work.Work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private final Date createOrderDate;
    private final Car car;
    private final String ownersName;
    private final List<Work> work;
    private final double price;
    private Date dateOfCompletion;
    private Date plannedStartDate;
    private OrderStatus status;
    private Master master;
    private Garage garage;

    public Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName, List<Work> work, double price) {
        this.createOrderDate = createOrderDate;
        this.plannedStartDate = plannedStartDate;
        this.car = car;
        this.ownersName = ownersName;
        this.work = work;
        this.price = price;
        status = OrderStatus.ACCEPTED;

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
    public String toString() {
        return "Order status: " + status + "/ car arrival time: " + createOrderDate + "/planned start date:" + plannedStartDate + "/ ownersName:" + ownersName + "/ work:" + work + "/ " + car
                +" \nprice: "+ price;
    }
}

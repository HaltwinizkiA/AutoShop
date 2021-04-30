package model.order;


import annotation.OrderStatus;
import model.car.Car;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private final Date createOrderDate;
    private Date dateOfCompletion;
    private final Car car;
    private final String ownersName;
    private final String work;
    private final double price;
    private Date plannedStartDate;
    private OrderStatus status;

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName, String work, double price) {
        this.createOrderDate = createOrderDate;
        this.plannedStartDate = plannedStartDate;
        this.car = car;
        this.ownersName = ownersName;
        this.work = work;
        this.price = price;
        status=OrderStatus.ACCEPTED;
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

    public Car getCar() {
        return car;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public String getWork() {
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
        return "Order status: " + status + "/ car arrival time: " + createOrderDate +"/plannde start date:"+plannedStartDate + "/ ownersName:" + ownersName + "/ work:" + work + "/ " + car;
    }
}

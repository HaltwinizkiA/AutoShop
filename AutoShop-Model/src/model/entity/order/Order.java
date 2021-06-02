package model.entity.order;


import annotations.CsvEntity;
import annotations.CsvProperty;
import annotations.Property;
import model.entity.Entity;
import model.entity.car.Car;
import model.entity.master.Master;
import model.entity.work.Work;
import model.enums.OrderStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@CsvEntity(fileName = "C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\reflection\\csv\\reflectOrders.csv", separator = ";")
public class Order extends Entity implements Cloneable {
    @CsvProperty(colomnNuber = 4, keyField = "date of create",property = Property.DateProperty)
    private Date createOrderDate;
    @CsvProperty(colomnNuber = 1, keyField = "car",property = Property.GetIdProperty)
    private Car car;
    @CsvProperty(colomnNuber = 2, keyField = "owners name")
    private String ownersName;
    @CsvProperty(colomnNuber = 7, keyField = "works id",property = Property.ListProperty)
    private List<Work> work;
    @CsvProperty(colomnNuber = 5, keyField = "price")
    private double price;
    @CsvProperty(colomnNuber = 9, keyField = "completion date",property = Property.DateProperty)
    private Date dateOfCompletion;
    @CsvProperty(colomnNuber = 6, keyField = "planned start date",property = Property.DateProperty)
    private Date plannedStartDate;
    @CsvProperty(colomnNuber = 3, keyField = "status")
    private OrderStatus status;
    @CsvProperty(colomnNuber = 8, keyField = "Master id",property = Property.GetIdProperty)
    private Master master;
    @CsvProperty(colomnNuber = 0, keyField = "id")
    private Integer id;

    public Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName, List<Work> work, double price, Integer id) {
        this.createOrderDate = createOrderDate;
        this.id = id;
        this.plannedStartDate = plannedStartDate;
        this.car = car;
        this.ownersName = ownersName;
        this.work = work;
        this.price = price;
        status = OrderStatus.ACCEPTED;

    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public void setCar(Car car) {
        this.car = car;
    }

    public String getOwnersName() {
        return ownersName;
    }

    public void setOwnersName(String ownersName) {
        this.ownersName = ownersName;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
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

    public void setCreateOrderDate(Date createOrderDate) {
        this.createOrderDate = createOrderDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

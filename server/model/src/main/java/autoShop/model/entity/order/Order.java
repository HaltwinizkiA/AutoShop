package autoShop.model.entity.order;

import autoShop.model.entity.Entity;
import autoShop.model.entity.car.Car;
import autoShop.model.entity.master.Master;
import autoShop.model.entity.work.Work;
import autoShop.model.enums.OrderStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@javax.persistence.Entity
@Table(name = "orders")
public class Order extends Entity implements Cloneable {

    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createOrderDate;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "master_id")
    private Master master;

    @Column(name = "owners_name")
    private String ownersName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_works", joinColumns = {@JoinColumn(name = "order_id")
    }, inverseJoinColumns = {@JoinColumn(name = "work_id")})
    private List<Work> works;
    @Column(name = "price")
    private double price;
    @Column(name = "completion_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfCompletion;
    @Column(name = "planned_start_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date plannedStartDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName, List<Work> work, double price, Integer id) {
        this.createOrderDate = createOrderDate;
        this.id = id;
        this.plannedStartDate = plannedStartDate;
        this.car = car;
        this.ownersName = ownersName;
//        this.work = work;
        this.price = price;
        status = OrderStatus.ACCEPTED;

    }

    public Order(Date createOrderDate, Date plannedStartDate, Car car, String ownersName) {
        this.createOrderDate = createOrderDate;
        this.plannedStartDate = plannedStartDate;
        this.car = car;
        this.ownersName = ownersName;
//        this.work = null;
        status = OrderStatus.ACCEPTED;

    }

    public Order() {
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
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

    public OrderStatus getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);

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

    public void setPrice() {
        for (Work work : works) {
            this.price += work.getPrice();
        }
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    @Override
    public String toString() {
        return "ID: " + id + " Order status: " + status + "/ car arrival time: " + createOrderDate + "/planned start date:" + plannedStartDate + "/ ownersName:" + ownersName + "/ work:" + works + "/ " + car
                + " \nprice: " + price;
    }
}

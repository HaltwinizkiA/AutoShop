package model.entity.master;



import model.enums.OrderStatus;
import model.enums.Specialty;
import model.entity.order.Order;

import java.io.Serializable;

public class Master implements Serializable {
    private final String name;
    private final String dateOfBirth;
    private final Specialty specialty;
    private final String phoneNumber;
    private Order order;
    private boolean status;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty, Integer id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.status = false;
        this.id=id;

    }

    public int getStatus() {
        if (status) {
            return 1;
        } else return 0;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
        this.order.setStatus(OrderStatus.IN_PROGRESS);
        this.status = true;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Master name: " + name + "/ date of birth: " + dateOfBirth + "/ phoneNumber: " + phoneNumber + "/ specialty: " + specialty + "/Order in performance: " + order;
    }
}


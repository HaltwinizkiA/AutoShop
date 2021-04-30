package model.master;


import annotation.OrderStatus;
import annotation.Specialty;
import model.order.Order;

import java.io.Serializable;

public class Master implements Serializable {
    private final String name;
    private final String dateOfBirth;
    private final Specialty specialty;
    private final String phoneNumber;
    private Order order;
    private int status;



    public int getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.status=0;

    }

    public void setOrder(Order order) {
        this.order = order;
        this.order.setStatus(OrderStatus.IN_PROGRESS);
        this.status=1;
    }

    @Override
    public String toString() {
        return "Master name: " + name + "/ date of birth: " + dateOfBirth + "/ phoneNumber: " + phoneNumber + "/ specialty: " + specialty+"/Order in performance: "+order;
    }
}


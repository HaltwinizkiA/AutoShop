package model.entity.garage;


import model.entity.car.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Garage implements Serializable {
    private int capacity;
    private List<Car> carsList;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Garage(int capacity, Integer id) {
        this.capacity = capacity;
        this.id=id;
        carsList=new ArrayList<>();
    }

    @Override
    public String toString() {
        return carsList.toString();
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<Car> carsList) {
        this.carsList = carsList;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

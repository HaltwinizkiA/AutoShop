package model.garage;


import model.car.Car;

import java.io.Serializable;
import java.util.Arrays;

public class Garage implements Serializable {
    private int capacity;
    private Car[] cars;

    @Override
    public String toString() {
        return Arrays.toString(cars);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public Garage(int capacity) {
        this.capacity = capacity;
        this.cars=new Car[capacity];
    }


    public int getCapacity() {
        return capacity;
    }
}

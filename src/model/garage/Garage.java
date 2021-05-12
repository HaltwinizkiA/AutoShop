package model.garage;


import model.car.Car;

import java.io.Serializable;
import java.util.Arrays;

public class Garage implements Serializable {
    private int capacity;
    private Car[] cars;//сделать лист гаражей .лист машин

    public Garage(int capacity) {
        this.capacity = capacity;
        this.cars = new Car[capacity];
    }

    @Override
    public String toString() {
        return Arrays.toString(cars);
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

package model.car;

import java.io.Serializable;

public class Car implements Serializable {
    private final  String mark;
    private final  String model;
    private final  String color;
    private final  String number;

    @Override
    public String toString() {
        return "Car mark: " + mark + "/ model: " + model + "/ color: " + color +"/ number: " + number;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public Car(String mark, String model, String color, String number) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.number = number;
    }
}

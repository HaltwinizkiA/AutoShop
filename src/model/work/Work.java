package model.work;

import java.io.Serializable;

public class Work implements Serializable {
    private final String name;
    private final double price;

    @Override
    public String toString() {
        return "Work: "+name+"  price= "+price ;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Work(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

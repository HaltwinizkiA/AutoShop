package model.work;

import java.io.Serializable;

public class Work implements Serializable {
    private final String name;
    private final double price;

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

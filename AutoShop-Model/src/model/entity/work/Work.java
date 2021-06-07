package model.entity.work;


import annotations.CsvEntity;
import annotations.CsvProperty;
import model.entity.Entity;

import java.io.Serializable;

@CsvEntity(fileName = "C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\reflection\\csv\\reflectWorks.csv", separator = ";")
public class Work extends Entity {
    @CsvProperty(colomnNuber = 1, keyField = "name")
    private final String name;
    @CsvProperty(colomnNuber = 2, keyField = "price")
    private final double price;
    @CsvProperty(colomnNuber = 0, keyField = "id")
    private Integer id;

    public Work(String name, double price, Integer id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }
    public Work() {
        this.name = null;
        this.price = 0;
        this.id = null;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;

    }

    @Override
    public String toString() {
        return "Work: " + name + "  price= " + price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

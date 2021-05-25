package model.entity.work;


import java.io.Serializable;

public class Work implements Serializable {
    private final String name;
    private final double price;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;

    }

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

    public Work(String name, double price,Integer id) {
        this.name = name;
        this.price = price;
        this.id=id;
    }
}

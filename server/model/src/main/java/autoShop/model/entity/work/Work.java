package autoShop.model.entity.work;


import autoShop.annotation.CsvEntity;
import autoShop.annotation.CsvProperty;
import autoShop.model.entity.Entity;
import autoShop.model.entity.order.Order;


import javax.persistence.*;
import java.util.List;


@CsvEntity(fileName = "C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\reflection\\csv\\reflectWorks.csv", separator = ";")
@javax.persistence.Entity
@Table(name = "works")
public class Work extends Entity {

    @CsvProperty(colomnNuber = 1, keyField = "name")
    @Column(name = "name")
    private  String name;
    @CsvProperty(colomnNuber = 2, keyField = "price")
    @Column(name = "price")
    private  double price;

    @CsvProperty(colomnNuber = 0, keyField = "id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Work(String name, double price, Integer id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Work(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Work() {

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
        return "ID: " + id + " Work: " + name + "  price= " + price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

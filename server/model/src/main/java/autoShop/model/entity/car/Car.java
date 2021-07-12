package autoShop.model.entity.car;



import autoShop.model.entity.Entity;
import autoShop.model.entity.garage.Garage;
import autoShop.model.entity.order.Order;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "cars")
public class Car extends Entity {
    @Column(name = "mark")
    private  String mark;
    @Column(name= "model")
    private  String model;
    @Column(name = "color")
    private  String color;
    @Column(name = "number")
    private String number;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToMany
    @JoinTable (name="box_cars",
            joinColumns=@JoinColumn (name="car_id"),
            inverseJoinColumns=@JoinColumn(name="box_id"))
    private List<Garage> box;
    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<Garage> getBox() {
        return box;
    }

    public void setBox(List<Garage> box) {
        this.box = box;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @OneToOne (optional=false, mappedBy="car")
    private Order order;

    public Car() {
    }
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Car(String mark, String model, String color, String number,Integer id) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.number = number;
        this.id=id;
    }
    public Car(String mark, String model, String color, String number) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.number = number;

    }

    @Override
    public String toString() {
        return "Car mark: " + mark + "/ model: " + model + "/ color: " + color + "/ number: " + number;
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
}

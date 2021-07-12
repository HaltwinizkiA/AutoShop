package autoShop.model.entity.garage;

import autoShop.model.entity.Entity;
import autoShop.model.entity.car.Car;
import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "box")
public class Garage extends Entity {
    @Column(name = "capacity")
    private int capacity;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToMany
    @JoinTable (name="box_cars",
            joinColumns=@JoinColumn (name="box_id"),
            inverseJoinColumns=@JoinColumn(name="car_id"))
    private List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public Garage() {
    }

    public Garage(int capacity, Integer id) {
        this.capacity = capacity;
        this.id = id;

    }

    @Override
    public String toString() {
        return "Garage{" +
                "capacity=" + capacity +
                ", id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

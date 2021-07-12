package autoShop.model.entity.master;


import autoShop.annotation.CsvEntity;
import autoShop.annotation.CsvProperty;
import autoShop.model.entity.Entity;
import autoShop.model.entity.order.Order;
import autoShop.model.enums.Specialty;

import javax.persistence.*;

@CsvEntity(fileName = "C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\reflection\\csv\\reflectMasters.csv", separator = ";")
@javax.persistence.Entity
@Table(name = "masters")
public class Master extends Entity {
    @CsvProperty(colomnNuber = 1, keyField = "name")
    @Column(name = "name")
    private final String name;
    @CsvProperty(colomnNuber = 5, keyField = "date of birth")
    @Column(name = "date_birth")
    private final String dateOfBirth;
    @CsvProperty(colomnNuber = 3, keyField = "specialty")
    @Column(name = "specialty")
    @Enumerated(EnumType.STRING)
    private final Specialty specialty;
    @CsvProperty(colomnNuber = 4, keyField = "phone number")
    @Column(name = "phone_number")
    private final String phoneNumber;
    @CsvProperty(colomnNuber = 2, keyField = "status")
    @Column(name = "status")
    private final boolean status;
    @CsvProperty(colomnNuber = 0, keyField = "id")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne (optional=false, mappedBy="master")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty, Integer id, String satus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.status = satus.equals("true");
        this.id = id;

    }

    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty, Integer id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.status = false;
        this.id = id;

    }
    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.status = false;


    }

    public Master() {
        this.name = null;
        this.dateOfBirth = null;
        this.phoneNumber = null;
        this.specialty = null;
        this.status = false;
        this.id = 0;

    }

    public int getStatus() {
        if (status) {
            return 1;
        } else return 0;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Master name: " + name + "/ date of birth: " + dateOfBirth + "/ phoneNumber: " + phoneNumber + "/ specialty: " + specialty;
    }
}


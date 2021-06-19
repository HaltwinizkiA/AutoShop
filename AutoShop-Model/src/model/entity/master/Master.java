package model.entity.master;


import annotations.CsvEntity;
import annotations.CsvProperty;
import annotations.Property;
import model.entity.Entity;
import model.enums.Specialty;

import java.io.Serializable;

@CsvEntity(fileName = "C:\\Users\\37533\\Projects\\AutoShop\\dataBase\\reflection\\csv\\reflectMasters.csv", separator = ";")
public class Master extends Entity {
    @CsvProperty(colomnNuber = 1, keyField = "name")
    private final String name;
    @CsvProperty(colomnNuber = 5, keyField = "date of birth")
    private final String dateOfBirth;
    @CsvProperty(colomnNuber = 3, keyField = "specialty")
    private final Specialty specialty;
    @CsvProperty(colomnNuber = 4, keyField = "phone number")
    private final String phoneNumber;
    @CsvProperty(colomnNuber = 2, keyField = "status")
    private final boolean status;
    @CsvProperty(colomnNuber = 0, keyField = "id")
    private Integer id;

    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty, Integer id, String satus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        if (satus.equals("true")){
            this.status=true;
        }else {
            this.status=false;
        }
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
        return "ID: "+id+" Master name: " + name + "/ date of birth: " + dateOfBirth + "/ phoneNumber: " + phoneNumber + "/ specialty: " + specialty;
    }
}


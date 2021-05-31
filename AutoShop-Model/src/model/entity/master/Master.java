package model.entity.master;


import annotations.CsvEntity;
import annotations.CsvProperty;
import model.enums.Specialty;

import java.io.Serializable;
@CsvEntity(fileName = "C:\\\\Users\\\\37533\\\\Projects\\\\AutoShop\\\\dataBase\\\\csv\\\\masters.csv",separator = ";")
public class Master implements Serializable {
    @CsvProperty(colomnNuber = )
    private final String name;
    private final String dateOfBirth;
    private final Specialty specialty;
    private final String phoneNumber;
    private final boolean status;
    private Integer id;

    public Master(String name, String dateOfBirth, String phoneNumber, Specialty specialty, Integer id) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.specialty = specialty;
        this.status = false;
        this.id = id;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Master name: " + name + "/ date of birth: " + dateOfBirth + "/ phoneNumber: " + phoneNumber + "/ specialty: " + specialty;
    }
}


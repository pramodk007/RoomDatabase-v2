package com.androiddev.roomdatabase_v2.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Person_table")
public class Person {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String email;
    String number;
    String pinCode;
    String city;

    @Ignore
    public Person(int id, String name, String email, String number, String pinCode, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.pinCode = pinCode;
        this.city = city;
    }

    public Person(String name, String email, String number, String pinCode, String city) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.pinCode = pinCode;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

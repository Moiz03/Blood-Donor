package com.example.smd.recyclerviewtest.Models;


import androidx.annotation.NonNull;

public class DonorsData {
    private int id;
    private String name;
    private String status;
    private String availability;
    private String city;
    private String blood_group;
    private String phone_no;

    public DonorsData() {

    }

    @NonNull
    @Override
    public String toString() {
        return "DonorsData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", availability='" + availability + '\'' +
                ", city='" + city + '\'' +
                ", blood_group='" + blood_group + '\'' +
                ", phone_no='" + phone_no + '\'' +
                '}';
    }

    public DonorsData(int id, String name, String status, String availability, String city, String blood_group, String phone_no) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.availability = availability;
        this.city = city;
        this.blood_group = blood_group;
        this.phone_no = phone_no;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }



}

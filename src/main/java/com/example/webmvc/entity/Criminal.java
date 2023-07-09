package com.example.webmvc.entity;

public class Criminal extends AbstractEntity {
    private int criminalId;
    private String criminalName;
    private int age;
    private String description;

    public Criminal(int criminalId, String criminalName, int age, String description) {
        this.criminalId = criminalId;
        this.criminalName = criminalName;
        this.age = age;
        this.description = description;
    }

    public int getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(int criminalId) {
        this.criminalId = criminalId;
    }

    public String getCriminalName() {
        return criminalName;
    }

    public void setCriminalName(String criminalName) {
        this.criminalName = criminalName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

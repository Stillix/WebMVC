package com.example.webmvc.entity;

import java.util.StringJoiner;

public class MissingPerson {
    private int missingPersonId;
    private String MissingPersonName;
    private String MissingPersonSurName;
    private int age;
    private String description;

    public MissingPerson(int missingPersonId, String missingPersonName, String missingPersonSurName, int age, String description) {
        this.missingPersonId = missingPersonId;
        MissingPersonName = missingPersonName;
        MissingPersonSurName = missingPersonSurName;
        this.age = age;
        this.description = description;
    }

    public int getMissingPersonId() {
        return missingPersonId;
    }

    public void setMissingPersonId(int missingPersonId) {
        this.missingPersonId = missingPersonId;
    }

    public String getMissingPersonName() {
        return MissingPersonName;
    }

    public void setMissingPersonName(String missingPersonName) {
        MissingPersonName = missingPersonName;
    }

    public String getMissingPersonSurName() {
        return MissingPersonSurName;
    }

    public void setMissingPersonSurName(String missingPersonSurName) {
        MissingPersonSurName = missingPersonSurName;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", MissingPerson.class.getSimpleName() + "[", "]")
                .add("MissingPersonName='" + MissingPersonName + "'")
                .add("MissingPersonSurName='" + MissingPersonSurName + "'")
                .add("age=" + age)
                .add("description='" + description + "'")
                .toString();
    }
}

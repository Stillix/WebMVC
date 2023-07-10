package com.example.webmvc.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class Person {
    private int personId;
    private String personName;
    private String personSurname;
    private int age;
    private String description;

    public Person(int personId, String personName, String personSurname, int age, String description) {
        this.personId = personId;
        this.personName = personName;
        this.personSurname = personSurname;
        this.age = age;
        this.description = description;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && age == person.age && Objects.equals(personName, person.personName) && Objects.equals(personSurname, person.personSurname) && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, personName, personSurname, age, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("personId=" + personId)
                .add("personName='" + personName + "'")
                .add("personSurname='" + personSurname + "'")
                .add("age=" + age)
                .add("description='" + description + "'")
                .toString();
    }
}

package com.example.webmvc.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class Person extends AbstractEntity {
    private int personId;
    private String personName;
    private String personSurname;
    private int personAge;
    private String description;
    private int PersonStatusId;
    private Person(){
    }
    public Person(int personId, String personName, String personSurname, int personAge, String description, int personStatusId) {
        this.personId = personId;
        this.personName = personName;
        this.personSurname = personSurname;
        this.personAge = personAge;
        this.description = description;
        PersonStatusId = personStatusId;
    }

    public int getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public int getPersonAge() {
        return personAge;
    }

    public String getDescription() {
        return description;
    }

    public int getPersonStatusId() {
        return PersonStatusId;
    }

    public static Builder newBuilder() {
        return new Person().new Builder();
    }


    public class Builder {

        private Builder() {
        }

        public void setPersonId(int personId) {
            Person.this.personId = personId;
        }

        public void setPersonName(String personName) {
            Person.this.personName = personName;
        }

        public void setPersonSurname(String personSurname) {
            Person.this.personSurname = personSurname;
        }

        public void setPersonAge(int personAge) {
            Person.this.personAge = personAge;
        }

        public void setDescription(String description) {
            Person.this.description = description;
        }

        public void setPersonStatusId(int personStatusId) {
            Person.this.PersonStatusId = personStatusId;
        }
        public Person build() {
            return Person.this;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId && personAge == person.personAge && PersonStatusId == person.PersonStatusId && Objects.equals(personName, person.personName) && Objects.equals(personSurname, person.personSurname) && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, personName, personSurname, personAge, description, PersonStatusId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("personId=" + personId)
                .add("personName='" + personName + "'")
                .add("personSurname='" + personSurname + "'")
                .add("personAge=" + personAge)
                .add("description='" + description + "'")
                .add("PersonStatusId=" + PersonStatusId)
                .toString();
    }
}

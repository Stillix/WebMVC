package com.example.webmvc.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class Person extends AbstractEntity {
    private int personId;
    private String personName;
    private String personSurname;
    private int personAge;
    private String description;
    private String personStatus;
    private int titleId;

    private Person() {
    }

    public int getTitleId() {
        return titleId;
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

    public String getPersonStatus() {
        return personStatus;
    }

    public static Builder newBuilder() {
        return new Person().new Builder();
    }


    public class Builder {

        private Builder() {
        }

        public Builder setPersonId(int personId) {
            Person.this.personId = personId;
            return this;
        }
        public Builder setTitleId(int titleId) {
            Person.this.titleId = titleId;
            return this;
        }
        public Builder setPersonName(String personName) {
            Person.this.personName = personName;
            return this;
        }

        public Builder setPersonSurname(String personSurname) {
            Person.this.personSurname = personSurname;
            return this;
        }

        public Builder setPersonAge(int personAge) {
            Person.this.personAge = personAge;
            return this;
        }

        public Builder setDescription(String description) {
            Person.this.description = description;
            return this;
        }

        public Builder setPersonStatus(String personStatus) {
            Person.this.personStatus = personStatus;
            return this;
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
        return personId == person.personId && personAge == person.personAge && personStatus == person.personStatus && Objects.equals(personName, person.personName) && Objects.equals(personSurname, person.personSurname) && Objects.equals(description, person.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, personName, personSurname, personAge, description, personStatus);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("personId=" + personId)
                .add("personName='" + personName + "'")
                .add("personSurname='" + personSurname + "'")
                .add("personAge=" + personAge)
                .add("description='" + description + "'")
                .add("personStatus=" + personStatus)
                .toString();
    }
}

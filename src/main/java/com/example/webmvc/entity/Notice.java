package com.example.webmvc.entity;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Notice extends AbstractEntity {
    private int noticeId;
    private String title;
    private int userId;
    private String personName;
    private String personSurname;
    private int personAge;
    private String personStatus;
    private String description;
    private int executionTime;
    private Timestamp publicationDateTime;
    private int reward;
    private int statusId;


    private Notice() {
    }

    public String getTitle() {
        return title;
    }


    public int getNoticeId() {
        return noticeId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPersonStatus() {
        return personStatus;
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

    public int getExecutionTime() {
        return executionTime;
    }

    public Timestamp getPublicationDateTime() {
        return publicationDateTime;
    }

    public int getReward() {
        return reward;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getDescription() {
        return description;
    }

    public static Builder newBuilder() {
        return new Notice().new Builder();
    }

    public class Builder {

        private Builder() {
        }


        public Builder setNoticeId(int noticeId) {
            Notice.this.noticeId = noticeId;
            return this;
        }

        public Builder setTitle(String title) {
            Notice.this.title = title;
            return this;
        }

        public Builder setUserId(int userId) {
            Notice.this.userId = userId;
            return this;
        }

        public Builder setNamePerson(String namePerson) {
            Notice.this.personName = namePerson;
            return this;
        }
        public Builder setPersonStatus(String personStatus) {
            Notice.this.personStatus = personStatus;
            return this;
        }
        public Builder setSurnamePerson(String surnamePerson) {
            Notice.this.personSurname = surnamePerson;
            return this;
        }

        public Builder setAge(int age) {
            Notice.this.personAge = age;
            return this;
        }


        public Builder setExecutionTime(int executionTime) {
            Notice.this.executionTime = executionTime;
            return this;
        }

        public Builder setPublicationDateTime(Timestamp publicationDateTime) {
            Notice.this.publicationDateTime = publicationDateTime;
            return this;
        }

        public Builder setReward(int reward) {
            Notice.this.reward = reward;
            return this;
        }

        public Builder setStatusId(int statusId) {
            Notice.this.statusId = statusId;
            return this;
        }

        public Builder setDescription(String description) {
            Notice.this.description = description;
            return this;
        }
        public Builder setErrorMessage(Map<String,String> errorMessages) {
            Notice.this.errorMessages = errorMessages;
            return this;
        }

        public Notice build() {
            return Notice.this;
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return noticeId == notice.noticeId && userId == notice.userId && personAge == notice.personAge && executionTime == notice.executionTime && reward == notice.reward && statusId == notice.statusId && Objects.equals(title, notice.title) && Objects.equals(personName, notice.personName) && Objects.equals(personSurname, notice.personSurname) && Objects.equals(description, notice.description) && Objects.equals(publicationDateTime, notice.publicationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeId, title, userId, personName, personSurname, personAge, description, executionTime, publicationDateTime, reward, statusId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Notice.class.getSimpleName() + "[", "]")
                .add("noticeId=" + noticeId)
                .add("title='" + title + "'")
                .add("userId=" + userId)
                .add("namePerson='" + personName + "'")
                .add("surnamePerson='" + personSurname + "'")
                .add("age=" + personAge)
                .add("description='" + description + "'")
                .add("executionTime=" + executionTime)
                .add("publicationDateTime=" + publicationDateTime)
                .add("reward=" + reward)
                .add("statusId=" + statusId)
                .toString();
    }
}

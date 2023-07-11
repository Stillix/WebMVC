package com.example.webmvc.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

public class Statement extends AbstractEntity{
    private int statementId;
    private int userId;
    private int personId;
    private int executionTime;
    private Timestamp publicationDateTime;
    private int reward;
    private int statusId;
    private String description;

    public Statement(int statementId, int userId, int personId, int executionTime, Timestamp publicationDateTime, int reward, int statusId, String description) {
        this.statementId = statementId;
        this.userId = userId;
        this.personId = personId;
        this.executionTime = executionTime;
        this.publicationDateTime = publicationDateTime;
        this.reward = reward;
        this.statusId = statusId;
        this.description = description;
    }

    public int getStatementId() {
        return statementId;
    }

    public void setStatementId(int statementId) {
        this.statementId = statementId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    public Timestamp getPublicationDateTime() {
        return publicationDateTime;
    }

    public void setPublicationDateTime(Timestamp publicationDateTime) {
        this.publicationDateTime = publicationDateTime;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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
        Statement statement = (Statement) o;
        return statementId == statement.statementId && userId == statement.userId && personId == statement.personId && executionTime == statement.executionTime && reward == statement.reward && statusId == statement.statusId && Objects.equals(publicationDateTime, statement.publicationDateTime) && Objects.equals(description, statement.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statementId, userId, personId, executionTime, publicationDateTime, reward, statusId, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Statement.class.getSimpleName() + "[", "]")
                .add("statementId=" + statementId)
                .add("userId=" + userId)
                .add("personId=" + personId)
                .add("executionTime=" + executionTime)
                .add("publicationDateTime=" + publicationDateTime)
                .add("reward=" + reward)
                .add("statusId=" + statusId)
                .add("description='" + description + "'")
                .toString();
    }
}

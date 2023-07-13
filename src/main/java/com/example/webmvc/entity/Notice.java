package com.example.webmvc.entity;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

public class Notice extends AbstractEntity {
    private int noticeId;
    private String title;
    private int userId;
    private int personId;
    private int executionTime;
    private Timestamp publicationDateTime;
    private int reward;
    private int statusId;
    private String description;

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

    public int getPersonId() {
        return personId;
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

        public Builder setPersonId(int personId) {
            Notice.this.personId = personId;
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
        public Notice build() {
            return Notice.this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return noticeId == notice.noticeId && userId == notice.userId && personId == notice.personId && executionTime == notice.executionTime && reward == notice.reward && statusId == notice.statusId && Objects.equals(publicationDateTime, notice.publicationDateTime) && Objects.equals(description, notice.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeId, userId, personId, executionTime, publicationDateTime, reward, statusId, description);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Notice.class.getSimpleName() + "[", "]")
                .add("noticeId=" + noticeId)
                .add("title=" +title)
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

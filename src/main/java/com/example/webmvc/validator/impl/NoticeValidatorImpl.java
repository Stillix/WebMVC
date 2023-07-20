package com.example.webmvc.validator.impl;

import com.example.webmvc.entity.Notice;

import java.util.HashMap;
import java.util.Map;

import static com.example.webmvc.command.RequestAttributeName.*;

public class NoticeValidatorImpl {

    public static final String TITLE_REGEX = "^[a-zA-Z]{2,15}$";
    public static final String NAME_PERSON_REGEX = "^[a-zA-Z]{2,15}$";
    public static final String SURNAME_PERSON_REGEX = "^[a-zA-Z]{2,15}$";
    public static final String AGE_REGEX = "^(1[89]|[2-9]\\d)$";
//    public static final String DESCRIPTION_REGEX = "^[a-zA-Z0-9%$.,]*$";
    public static final String EXECUTION_TIME_REGEX = "^\\d+$";
    public static final String REWARD_REGEX = "^\\d+$";


    public Map<String, String> isValidNotice(Notice notice) {
        Map<String, String> errorMessages = new HashMap<>();
        if (!isValidTitle(notice.getTitle())) {
            errorMessages.put(ERROR_TITLE_MESSAGE, "Invalid title format");
        }
        if (!isValidNamePerson(notice.getPersonName())) {
            errorMessages.put(ERROR_NAME_PERSON_MESSAGE, "Invalid name format");
        }
        if (!isValidSurnamePerson(notice.getPersonSurname())) {
            errorMessages.put(ERROR_SURNAME_PERSON_MESSAGE, "Invalid surname format");
        }
        if (!isValidAge(String.valueOf(notice.getPersonAge()))) {
            errorMessages.put(ERROR_AGE_MESSAGE, "Invalid age format");
        }
//        if (!isValidDescription(notice.getDescription())) {
//            errorMessages.put(ERROR_DESCRIPTION_MESSAGE, "Invalid description format");
//        }
        if (!isValidExecutionTime(String.valueOf(notice.getExecutionTime()))) {
            errorMessages.put(ERROR_EXECUTION_TIME_MESSAGE, "Invalid execution time format");
        }
        if (!isValidReward(String.valueOf(notice.getReward()))) {
            errorMessages.put(ERROR_REWARD_MESSAGE, "Invalid reward format");
        }
        return errorMessages;
    }

    public boolean isValidTitle(String title) {
        return title != null && title.matches(TITLE_REGEX);
    }
    public boolean isValidNamePerson(String namePerson) {
        return namePerson != null && namePerson.matches(NAME_PERSON_REGEX);
    }
    public boolean isValidSurnamePerson(String surnamePerson) {
        return surnamePerson != null && surnamePerson.matches(SURNAME_PERSON_REGEX);
    }
    public boolean isValidAge(String age) {
        return age != null && age.matches(AGE_REGEX);
    }
//    public boolean isValidDescription(String description) {
//        return description != null && description.matches(DESCRIPTION_REGEX);
//    }
    public boolean isValidExecutionTime(String executionTime) {
        return executionTime != null && executionTime.matches(EXECUTION_TIME_REGEX);
    }
    public boolean isValidReward(String reward) {
        return reward != null && reward.matches(REWARD_REGEX);
    }


}

package com.example.webmvc.entity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEntity {

    Map<String, String> errorMessages = new HashMap<>();

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}

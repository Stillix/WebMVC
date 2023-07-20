package com.example.webmvc.util;

public class Validation {

    boolean isValid;
    String attributeName;
    String errorMessage;

    public Validation() {
    }

    public Validation(boolean isValid, String attributeName, String errorMessage) {
        this.isValid = isValid;
        this.attributeName = attributeName;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

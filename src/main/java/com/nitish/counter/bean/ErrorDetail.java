package com.nitish.counter.bean;


import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorDetail implements Serializable{

    private Long timeStamp;
    private HttpStatus status;
    private String message;
    private String path;

    public ErrorDetail() {
        status = HttpStatus.OK;
    }

    List<ValidationError> validationErrors;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public void addErrors(ValidationError validationError) {
        if(null == validationErrors){
            validationErrors = new ArrayList<ValidationError>();
        }
        this.validationErrors.add(validationError);
    }

}

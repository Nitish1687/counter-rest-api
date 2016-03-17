package com.nitish.counter.builder;


import com.nitish.counter.bean.ErrorDetail;
import com.nitish.counter.bean.ValidationError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorDetailBuilder {
    private Long timeStamp;
    private HttpStatus status;
    private String message;
    private String path;
    private List<ValidationError> validationErrors;

    private ErrorDetailBuilder(){

    }

    public static ErrorDetailBuilder builder(){
        return new ErrorDetailBuilder();
    }

    public ErrorDetailBuilder withTimeStamp(Long timeStamp){
        this.timeStamp = timeStamp;
        return this;
    }

    public ErrorDetailBuilder withStatus(HttpStatus status){
        this.status = status;
        return this;
    }

    public ErrorDetailBuilder withMessage(String message){
        this.message = message;
        return this;
    }

    public ErrorDetailBuilder withPath(String path){
        this.path = path;
        return this;
    }

    public ErrorDetailBuilder withValidationErrors(List validationErrors){
        this.validationErrors = validationErrors;
        return this;
    }

    public ErrorDetail build() {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setPath(this.path);
        errorDetail.setTimeStamp(this.timeStamp);
        errorDetail.setMessage(this.message);
        errorDetail.setStatus(this.status);
        errorDetail.setValidationErrors(this.validationErrors);
        return errorDetail;
    }
}

package com.nitish.counter.exception;

import com.nitish.counter.bean.ErrorDetail;
import com.nitish.counter.builder.ErrorDetailBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.StringUtils.isEmpty;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidFileReadException.class,InvalidWordDocumentRead.class})
    public ResponseEntity<ErrorDetail> handleInvalidReadException(InvalidFileReadException ex, HttpServletRequest request) {
        ErrorDetail errorDetail = ErrorDetailBuilder.builder()
                .withStatus(INTERNAL_SERVER_ERROR)
                .withMessage(ex.getMessage())
                .withPath(getPathInformation(request))
                .withTimeStamp(new Date().getTime()).build();
        return new ResponseEntity<>(errorDetail, errorDetail.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetail> runtimeExceptionHandler(RuntimeException runtimeException, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setMessage(runtimeException.getMessage());
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setPath(getPathInformation(request));
        return new ResponseEntity<>(errorDetail, errorDetail.getStatus());
    }

    private String getPathInformation(HttpServletRequest request) {
        return !isEmpty(request.getQueryString()) ? getCompleteServerPathWithQueryString(request) : getRequestURI(request);
    }

    private String getCompleteServerPathWithQueryString(HttpServletRequest request) {
        return request.getRequestURL().append("?").append(request.getQueryString()).toString();
    }

    private String getRequestURI(HttpServletRequest request) {
        return request.getRequestURL().toString();
    }

}

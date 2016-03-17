package com.nitish.counter.exception;

import com.nitish.counter.bean.ErrorDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

    @Mock
    private HttpServletRequest request;
    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    private static final String INVALID_USER_DATA = "Problem while FileRead";
    private static final String RUNTIME_EXCEPTION_HANDLER = "Runtime exception handler";
    private static final String PATH_INFO = "http://localhost:8080/mockUrl";
    private static final String QUERY_STRING = "email=text@gmail.com";

    @Test
    public void shouldHandleExceptionHappenedDuringFileRead() throws Exception {
        HttpStatus httpStatus = HttpStatus.valueOf(500);
        when(request.getQueryString()).thenReturn(QUERY_STRING);
        when(request.getRequestURL()).thenReturn(new StringBuffer(PATH_INFO));

        ResponseEntity<ErrorDetail> errorDetailResponseEntity = exceptionHandler.handleInvalidReadException(
                new InvalidFileReadException(INVALID_USER_DATA), request);

        ErrorDetail errorDetail = errorDetailResponseEntity.getBody();
        assertEquals(PATH_INFO + "?" + QUERY_STRING, errorDetail.getPath());
        assertEquals(INVALID_USER_DATA, errorDetail.getMessage());
        assertEquals(httpStatus, errorDetail.getStatus());

    }

    @Test
    public void shouldHandleForRuntimeException() throws Exception {
        RuntimeException runtimeException = new RuntimeException(RUNTIME_EXCEPTION_HANDLER);
        when(request.getQueryString()).thenReturn(null);
        when(request.getRequestURL()).thenReturn(new StringBuffer(PATH_INFO));

        ResponseEntity<ErrorDetail> errorDetailResponseEntity = exceptionHandler.runtimeExceptionHandler(runtimeException, request);
        ErrorDetail responseEntityBody = errorDetailResponseEntity.getBody();

        assertEquals(RUNTIME_EXCEPTION_HANDLER, responseEntityBody.getMessage());
        assertEquals(PATH_INFO, responseEntityBody.getPath());
    }
}
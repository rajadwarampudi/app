package com.voyado.elevate.app.searchservice.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = SearchServiceException.class)
    public ResponseEntity<String> handleException(SearchServiceException ex) {
        return new ResponseEntity<>(getErrorMessage(ex), HttpStatus.BAD_REQUEST);
    }

    private static String getErrorMessage(SearchServiceException ex) {
        return ex.getMessage() + ": Please follow the documentation from README file to set API Key and CSE ID, " +
                "set the values in application properties";
    }
}

package com.friday.handler;

import com.friday.exception.NoHouseNumberFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Diego de Souza on 16/11/18.
 */
@ControllerAdvice
@RestController
public class RequestExceptionHandler {

    @ExceptionHandler(NoHouseNumberFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String noHousenumberFoundHandler(NoHouseNumberFoundException e) {
        return "No house number found in the given address";
    }

}

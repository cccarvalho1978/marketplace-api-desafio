package com.cristiano.marketplace.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception throw messages validation
 * @author cristiano.carvalho
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ValidationException extends Exception{

    private static final long serialVersionUID = 1L;

    public ValidationException(List<String> messages){
        super(messages.toString());
    }
}
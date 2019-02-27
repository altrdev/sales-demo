package com.sales.taxes.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4395792583715728519L;

    public NotFoundException(String exception) {
        super(exception);
    }
}
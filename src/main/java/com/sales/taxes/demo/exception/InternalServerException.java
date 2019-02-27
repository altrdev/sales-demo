package com.sales.taxes.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    private static final long serialVersionUID = 4395792583715728519L;

    public InternalServerException(String exception) {
        super(exception);
    }
}
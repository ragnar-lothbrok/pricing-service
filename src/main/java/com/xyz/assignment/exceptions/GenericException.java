package com.xyz.assignment.exceptions;

import org.springframework.http.HttpStatus;

public abstract class GenericException extends RuntimeException {

    private Integer error;
    private Integer status;

    public GenericException(String message, Integer error) {
        super(message);
        this.error = error;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public GenericException(String message, Integer error, Integer status) {
        super(message);
        this.error = error;
        this.status = status;
    }

    public Integer getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }
}

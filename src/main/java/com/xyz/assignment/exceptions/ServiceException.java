package com.xyz.assignment.exceptions;

import com.xyz.assignment.constants.ErrorConstants;

/**
 * Used in all exception other than IMS
 */
public class ServiceException extends GenericException {

    public ServiceException(Integer errorCode, String message) {
        super(message, errorCode);
    }

    public ServiceException(Integer errorCode, String message, Integer httpStatusCode) {
        super(message, errorCode == null ? ErrorConstants.DEFAULT_EXCEPTION : errorCode, httpStatusCode);
    }

}

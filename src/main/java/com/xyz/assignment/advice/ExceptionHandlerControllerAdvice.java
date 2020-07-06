package com.xyz.assignment.advice;

import com.xyz.assignment.constants.ErrorConstants;
import com.xyz.assignment.dtos.ExceptionResponseDto;
import com.xyz.assignment.exceptions.ServiceException;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * It catches exception and convert into DTOs and send as part of response
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({ServiceException.class})
    @ResponseBody
    public ResponseEntity<ExceptionResponseDto> handleServiceException(
            final ServiceException exception, final HttpServletRequest request) {
        ExceptionResponseDto res = new ExceptionResponseDto(exception.getError(), exception.getMessage());
        return new ResponseEntity<>(res, exception.getStatus() != null ? HttpStatus.valueOf(exception.getStatus())
                : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseDto> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        ExceptionResponseDto res = new ExceptionResponseDto(ErrorConstants.MISSING_PARAMETER,
                String.format("%s parameter is missing", name));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    /**
     * This method acts as catch all
     *
     * @param exception the throwable exception
     * @return json response entity
     */
    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public ResponseEntity<ExceptionResponseDto> handleThrowable(final Throwable exception) {
        ExceptionResponseDto res = new ExceptionResponseDto(ErrorConstants.DEFAULT_EXCEPTION,
                exception.getMessage());
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle {@link NoHandlerFoundException} return 404 json response
     *
     * @param request the http servlet request
     * @param ex the exception that was thrown
     * @return response entity with 404 status
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    public ResponseEntity<ExceptionResponseDto> handleNoHandlerFoundException(final HttpServletRequest request,
                                                                              final NoHandlerFoundException ex) {
        ExceptionResponseDto res = new ExceptionResponseDto(ErrorConstants.HANDLER_NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(res, getHeaderMap(), HttpStatus.NOT_FOUND);
    }

    /**
     * This returns Headers containing Content Type
     */
    private MultiValueMap<String, String> getHeaderMap() {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("Content-type", Arrays.asList(MediaType.APPLICATION_JSON_VALUE));
        return headers;
    }

}

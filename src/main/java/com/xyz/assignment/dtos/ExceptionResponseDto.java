package com.xyz.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;

/**
 * Any error from API should follow below class pattern.
 */
@JsonInclude(Include.NON_NULL)
public class ExceptionResponseDto {

    private String reason;
    private String message;
    private HttpHeaders headers;

    @NotNull
    private Integer error;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public ExceptionResponseDto(@NotNull Integer error, String message) {
        this.message = message;
        this.error = error;
    }
}

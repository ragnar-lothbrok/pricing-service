package com.xyz.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Any API response will be part of this class.
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class ResponseDto<T> {

    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseDto(T data) {
        this.data = data;
    }
}

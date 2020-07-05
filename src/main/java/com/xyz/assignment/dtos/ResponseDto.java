package com.xyz.assignment.dtos;

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

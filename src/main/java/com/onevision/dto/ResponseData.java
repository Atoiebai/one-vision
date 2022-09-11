package com.onevision.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseData<T> {
    @JsonProperty("data")
    private T data;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("timestamp")
    private long timestamp;

    public ResponseData(T data) {
        this.data = data;
        this.errorMessage = "";
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseData(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseEntity<ResponseData<T>> response(T data) {
        return ResponseEntity.ok(new ResponseData<>(data));
    }

    public static <T> ResponseEntity<ResponseData<T>> response(T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseData<>(data), httpStatus);
    }

    public static <T> ResponseEntity<ResponseData<T>> response(ResponseData<T> responseData, HttpStatus status) {
        return new ResponseEntity<>(responseData, status);
    }

    public static <T> ResponseEntity<ResponseData<T>> response(String errorMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseData<>(null, errorMessage), httpStatus);
    }


}


package com.example.storebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String message;
    private Object data;
    private int status;

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}

package com.example.springbootapp.error;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends Exception {
    private String errorCode;

    private List<ErrorDetail> errorDetailList;

    public ServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(String message, String errorCode, List<ErrorDetail> errorDetailList) {
        super(message);
        this.errorCode = errorCode;
        this.errorDetailList = errorDetailList;
    }
}

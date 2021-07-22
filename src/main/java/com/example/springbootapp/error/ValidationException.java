package com.example.springbootapp.error;

import com.example.springbootapp.enums.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

//@Data
//@EqualsAndHashCode(callSuper = false)
//public class ValidationException extends ServiceException {
//    private String object;
//    private String field;
//    private Serializable rejectedValue;
//
////    public ValidationException(String errorCode, String message, String object, String field, Serializable rejectedValue) {
////        super(errorCode, message);
////        this.object = object;
////        this.field = field;
////        this.rejectedValue = rejectedValue;
////    }
//
//    public ValidationException(String message, String object, String field, Serializable rejectedValue) {
//        super(ErrorCode.VALIDATION_ERROR.name(), message);
//        this.object = object;
//        this.field = field;
//        this.rejectedValue = rejectedValue;
//    }
//
//
//}

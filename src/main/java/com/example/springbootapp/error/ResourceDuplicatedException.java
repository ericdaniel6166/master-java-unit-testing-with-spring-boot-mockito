package com.example.springbootapp.error;

import com.example.springbootapp.enums.ErrorCode;

public class ResourceDuplicatedException extends ServiceException {
    public ResourceDuplicatedException(String resource) {
        super(ErrorCode.RESOURCE_DUPLICATED_ERROR.name(), String.format("%s is duplicated", resource));
    }
}

package com.example.springbootapp.error;

import com.example.springbootapp.enums.ErrorCode;

public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException(String resource) {
        super(ErrorCode.RESOURCE_NOT_FOUND_ERROR.name(), String.format("%s does not exist", resource));
    }
}

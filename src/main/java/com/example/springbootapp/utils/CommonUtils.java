package com.example.springbootapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonUtils {

    public static String asJsonString(final Object object) {
        try {
            log.info("Start GET_JSON_STRING, object: {}", object);
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(object);
            log.info("End GET_JSON_STRING, jsonContent: {}", jsonContent);
            return jsonContent;
        } catch (JsonProcessingException e) {
            log.warn("Cannot get json string from object, object: {}, exception message: {}, exception cause: {}",
                    object, e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        }
    }
}

package com.zoomgy.portfolio.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class CustomResponseBuilder {

    public static Map<String,Object> buildError(String message, Object statusValue){
        Map<String,Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("message",message);
        error.put("status", statusValue);
        return error;
    }

    public static Map<String,Object> buildError(Object payload,String message, Object statusValue){
        Map<String,Object> error = new HashMap<>();
        error.put("payload",payload);
        error.put("timestamp", LocalDateTime.now());
        error.put("message",message);
        error.put("status", statusValue);
        return error;
    }


    public static Map<String,Object> buildResponse(Object payload,Object statusValue){
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp",LocalDateTime.now());
        response.put("message","success");
        response.put("payload",payload);
        response.put("status",statusValue);
        return response;
    }
}

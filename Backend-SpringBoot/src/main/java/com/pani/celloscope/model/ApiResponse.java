package com.pani.celloscope.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message = "";
    private Map<String, Object> data = new HashMap<>();
    private int statusCode = 0;
    private static ApiResponse INSTANCE = null;

    public static ApiResponse getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiResponse();
        }
        return INSTANCE;
    }
}

package com.summer.xblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonDTO {
    private boolean success;
    private String message;
    private Object data;

    public static CommonDTO success(String message, Object data) {
        return new CommonDTO(true, message, data);
    }

    public static CommonDTO success(String message) {
        return success(message, null);
    }

    public static CommonDTO success(Object data) {
        return success(null, data);
    }

    public static CommonDTO fail(String message, Object data) {
        return new CommonDTO(false, message, data);
    }

    public static CommonDTO fail(String message) {
        return fail(message, null);
    }

    public static CommonDTO fail(Object data) {
        return fail(null, data);
    }

}

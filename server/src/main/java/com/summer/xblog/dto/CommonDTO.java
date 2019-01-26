package com.summer.xblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonDTO<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> CommonDTO<T> success(String message, T data) {
        return new CommonDTO<>(true, message, data);
    }

    public static <T> CommonDTO<T> success(String message) {
        return success(message, null);
    }

    public static <T> CommonDTO<T> success(T data) {
        return success(null, data);
    }

    public static <T> CommonDTO<T> fail(String message, T data) {
        return new CommonDTO<>(false, message, data);
    }

    public static <T> CommonDTO<T> fail(String message) {
        return fail(message, null);
    }

    public static <T> CommonDTO<T> fail(T data) {
        return fail(null, data);
    }

}

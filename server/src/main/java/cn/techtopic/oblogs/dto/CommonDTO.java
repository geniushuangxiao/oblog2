package cn.techtopic.oblogs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonDTO {
    private boolean success;
    private String message;
    private Object data;
}

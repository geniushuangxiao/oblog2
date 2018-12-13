package com.summer.xblog.dto;

import lombok.Data;

@Data
public class Register {
    private String username;
    private String password;
    private String password2;
    private String email;
}

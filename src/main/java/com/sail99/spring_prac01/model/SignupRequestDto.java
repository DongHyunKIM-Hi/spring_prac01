package com.sail99.spring_prac01.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {


    private String username;
    private String password;
    private String password_check;
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
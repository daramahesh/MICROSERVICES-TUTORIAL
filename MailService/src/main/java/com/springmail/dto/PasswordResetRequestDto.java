package com.springmail.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDto {

    private String userName;
    private String phoneNumber;
    private String oneTimePassword;
}

package com.springmail.dto;

import lombok.Data;

@Data
public class PasswordResetResponseDto {

    private OtpStatus status;
    private String message;
}

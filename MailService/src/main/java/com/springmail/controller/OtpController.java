package com.springmail.controller;

import com.springmail.dto.PasswordResetRequestDto;
import com.springmail.service.TwilioOTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {
    @Autowired
    private TwilioOTPService twilioOTPService;

    @PostMapping("send-otp")
    public String sendOtp(@RequestBody PasswordResetRequestDto passwordResetRequestDto) {
        return twilioOTPService.sendOTPForPasswordReset(passwordResetRequestDto);
    }

    @PostMapping("/validate")
    public String validate(@RequestParam("userName") String userName,@RequestParam("otp") String otp) {
        return twilioOTPService.validate(userName,otp);
    }
}

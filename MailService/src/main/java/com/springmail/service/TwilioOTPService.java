package com.springmail.service;

import com.springmail.config.TwilioConfig;
import com.springmail.dto.PasswordResetRequestDto;
import com.springmail.dto.PasswordResetResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {

    @Autowired
    private TwilioConfig twilioConfig;

    Map<String, String> map = new HashMap<>();

    public String sendOTPForPasswordReset (PasswordResetRequestDto passwordResetRequestDto) {

        PhoneNumber to = new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrailNumber());
        String otp = generateOtp();
        String otpMessage =  "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";

        map.put(passwordResetRequestDto.getUserName(),otp);
        System.out.println(map);


        Message message = Message
                .creator(to,from,otpMessage)
                .create();
        return "otp sent successfully";
    }

    private String generateOtp() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

    public String validate(String userName, String otp) {
        if(otp.equals(map.get(userName))) {
            map.remove(userName,otp);
            return "otp is valid";
        }
        else {
            return "otp is invalid";
        }
    }
}

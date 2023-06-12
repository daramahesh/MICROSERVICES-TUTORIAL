package com.springmail.controller;

import com.springmail.dto.MailRequestDto;
import com.springmail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/mail")
    public String sendMail(@RequestBody MailRequestDto mailRequestDto) {
        return emailService.sendMail(mailRequestDto);
    }

    @PostMapping("/mailWithAttachment")
    public String sendMailWithAttachment(@RequestParam String to,
                                         @RequestParam MultipartFile attachment,
                                         @RequestParam String subject,
                                         @RequestParam String text) throws MessagingException, IOException {
        return emailService.sendMailWithAttachment(to, attachment,subject,text);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody MailRequestDto mailRequestDto) throws MessagingException {
        return emailService.forgotPassword(mailRequestDto);
    }
}

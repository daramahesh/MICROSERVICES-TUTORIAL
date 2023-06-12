package com.springmail.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.springmail.dto.MailRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(MailRequestDto mailRequestDto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailRequestDto.getTo());
        simpleMailMessage.setSubject(mailRequestDto.getSubject());
        simpleMailMessage.setText(mailRequestDto.getText());

        javaMailSender.send(simpleMailMessage);

        return "mail sent successfully";
    }

    public String sendMailWithAttachment(String to, MultipartFile attachment,String subject, String text) throws MessagingException, IOException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(text);

        byte[] bytes = attachment.getBytes();
        String fileName = attachment.getOriginalFilename().toString();
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);

        messageHelper.addAttachment(fileName, byteArrayResource);
        javaMailSender.send(mimeMessage);

        return "mail sent successfully";
    }

    public String forgotPassword(MailRequestDto mailRequestDto) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        String password = randomGeneratedPassword();

        messageHelper.setText("your reset password is" + password);
        messageHelper.setTo(mailRequestDto.getTo());
        messageHelper.setSubject(mailRequestDto.getSubject());

        javaMailSender.send(message);
        return "reset password has been sent to your mail";

    }

    public String randomGeneratedPassword() {
        String password = UUID.randomUUID().toString();
        return password.substring(0, 8);
    }
}

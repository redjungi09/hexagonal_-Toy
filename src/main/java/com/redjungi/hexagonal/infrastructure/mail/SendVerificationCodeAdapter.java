package com.redjungi.hexagonal.infrastructure.mail;

import com.redjungi.hexagonal.application.mail.port.out.SendVerificationCodePort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendVerificationCodeAdapter implements SendVerificationCodePort {

    private final JavaMailSender mailSender;

    @Override
    public void send(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("이메일 인증 코드");
        message.setText("인증번호: " + code);
        mailSender.send(message);
    }
}

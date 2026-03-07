package com.redjungi.hexagonal.application.mail.port.out;

public interface SendVerificationCodePort {
    void send(String email, String code);
}

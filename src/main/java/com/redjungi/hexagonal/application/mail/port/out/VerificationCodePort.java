package com.redjungi.hexagonal.application.mail.port.out;

public interface VerificationCodePort {
    void saveCode(String token, String code, long expirationMilliseconds);
}

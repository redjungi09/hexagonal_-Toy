package com.redjungi.hexagonal.application.common.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGenerator {
    public String getToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}

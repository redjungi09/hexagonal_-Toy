package com.redjungi.hexagonal.application.user.data;

public class SignUpData {

    public record Command(
            String username,
            String password,
            String email
    ){
    }

    public record Result(
            String message
    ){
    }
}

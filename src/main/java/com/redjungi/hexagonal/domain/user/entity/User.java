package com.redjungi.hexagonal.domain.user.entity;


public record User(
        Long id,
        String username,
        String password,
        String email
) {
    public   static User create(String username, String password, String email){
        return new User(
          null,
          username,
          password,
          email
        );
    }
}

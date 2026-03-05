package com.redjungi.hexagonal.domain.user.entity;


import com.redjungi.hexagonal.domain.user.entity.enums.Role;

import java.time.Instant;

public record User(
        Long id,
        String username,
        String password,
        String email,
        Role role,
        Instant createAt,
        Instant updatedAt
) {
    public static User create(String username, String password, String email){
        return new User(
        null,
            username,
            password,
            email,
            Role.USER,
            null,
            null
        );
    }
}

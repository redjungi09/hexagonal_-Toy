package com.redjungi.hexagonal.application.user.port.out;

import com.redjungi.hexagonal.domain.user.entity.User;

public interface PendingUserCachePort {
    void saveUser(User user, String token, long expirationMs);
}

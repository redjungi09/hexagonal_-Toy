package com.redjungi.hexagonal.adapter.persistence.auth;

import com.redjungi.hexagonal.application.user.port.out.PendingUserCachePort;
import com.redjungi.hexagonal.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class PendingUserPersistenceAdapter implements PendingUserCachePort {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void saveUser(User user, String token, long expirationMs) {
        String json = objectMapper.writeValueAsString(user);
        redisTemplate.opsForValue().set(token,json,expirationMs, TimeUnit.MILLISECONDS);
    }
}

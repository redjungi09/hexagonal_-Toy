package com.redjungi.hexagonal.application.user.port.out;

import com.redjungi.hexagonal.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserRepositoryPort {
    User save(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String emaill);
}

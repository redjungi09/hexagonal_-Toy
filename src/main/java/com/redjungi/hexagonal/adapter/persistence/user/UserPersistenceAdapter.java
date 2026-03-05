package com.redjungi.hexagonal.adapter.persistence.user;

import com.redjungi.hexagonal.application.user.port.out.UserRepositoryPort;
import com.redjungi.hexagonal.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = userJpaMapper.toJpaEntity(user);
        UserJpaEntity saved = userRepository.save(userJpaEntity);
        return userJpaMapper.toDomainUser(saved);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}

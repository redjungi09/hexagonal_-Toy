package com.redjungi.hexagonal.adapter.persistence.user;

import com.redjungi.hexagonal.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserJpaMapper {
    public UserJpaEntity toJpaEntity(User user){
        return new UserJpaEntity(
                user.id(),
                user.username(),
                user.password(),
                user.email(),
                user.role(),
                user.createAt(),
                user.updatedAt()
        );
    }

    public User toDomainUser(UserJpaEntity userJpaEntity){
        return new User(
                userJpaEntity.getId(),
                userJpaEntity.getUsername(),
                userJpaEntity.getPassword(),
                userJpaEntity.getEmail(),
                userJpaEntity.getRole(),
                userJpaEntity.getCreatedAt(),
                userJpaEntity.getUpdatedAt()
        );
    }
}

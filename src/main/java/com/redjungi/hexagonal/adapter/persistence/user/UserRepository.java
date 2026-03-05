package com.redjungi.hexagonal.adapter.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity,Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

package com.huyhn.ecommerce_backend.module.auth;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    @Query("select u from User u left join fetch u.authorities where u.email = :email")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE, key = "#email")
    Optional<User> findByEmailWithAuthorities(String email);
}

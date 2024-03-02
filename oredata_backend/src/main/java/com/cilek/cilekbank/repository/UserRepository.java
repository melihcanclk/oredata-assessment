package com.cilek.cilekbank.repository;

import com.cilek.cilekbank.model._User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<_User, UUID> {
    Optional<_User> findByUsername(String username);

    Optional<_User> findByEmail(String email);

}

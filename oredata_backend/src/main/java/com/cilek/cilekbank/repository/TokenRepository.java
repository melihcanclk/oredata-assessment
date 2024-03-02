package com.cilek.cilekbank.repository;

import com.cilek.cilekbank.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllTokensByUser_Username(String username);

    Optional<Token> findByAccessToken(String accessToken);

}

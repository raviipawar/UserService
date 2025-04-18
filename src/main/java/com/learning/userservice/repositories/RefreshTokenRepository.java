package com.learning.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.userservice.entities.RefreshToken;
import com.learning.userservice.entities.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByRefreshToken(String token); 
    void deleteByUser(User user);
}

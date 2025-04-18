package com.learning.userservice.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.userservice.entities.RefreshToken;
import com.learning.userservice.entities.User;
import com.learning.userservice.repositories.RefreshTokenRepository;
import com.learning.userservice.repositories.UserRepository;

@Service
public class RefereshTokenService {

	public long refreshTokenValidity = 1 * 60 * 60 * 1000;
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public RefreshToken createRefreshToken(String userName) {

		User user = userRepository.findByUsername(userName).get();
		RefreshToken refreshToken1 = user.getRefreshToken();

		if (refreshToken1 == null) {
			refreshToken1 = RefreshToken.builder().refreshToken(UUID.randomUUID().toString())
					.expiry(Instant.now().plusMillis(refreshTokenValidity))
					.user(userRepository.findByUsername(userName).get()).build();
		} else {
			refreshToken1.setExpiry(Instant.now().plusMillis(refreshTokenValidity));
		}
		user.setRefreshToken(refreshToken1);
		refreshTokenRepository.save(refreshToken1);
		return refreshToken1;
	}

	public RefreshToken verifyRefreshToken(String refreshToken) {

		RefreshToken refreshTokenOb = refreshTokenRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new RuntimeException("Given token does not exists"));
		if (refreshTokenOb.getExpiry().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(refreshTokenOb);
			throw new RuntimeException("Refresh token expired");
		}
		return refreshTokenOb;
	}

	public Optional<RefreshToken> findByToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken);
	}

	@Transactional
	public void logoutUser(User user) {
		refreshTokenRepository.deleteByUser(user);
	}

	@Transactional
	public void deleteToken(String token) {
		refreshTokenRepository.findByRefreshToken(token).ifPresent(refreshTokenRepository::delete);
	}
}

package com.file.demo.filedemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.file.demo.filedemo.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>{
	Optional<RefreshToken> findByRefreshToken(String token);
}

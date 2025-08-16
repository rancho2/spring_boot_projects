package com.file.demo.filedemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.file.demo.filedemo.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
	public Optional<User> findByEmail(String email);
}

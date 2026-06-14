package com.connectify.connectify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connectify.connectify.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

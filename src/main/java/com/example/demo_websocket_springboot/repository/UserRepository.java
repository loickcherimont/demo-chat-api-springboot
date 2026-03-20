package com.example.demo_websocket_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_websocket_springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}

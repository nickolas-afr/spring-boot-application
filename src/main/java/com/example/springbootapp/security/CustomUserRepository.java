package com.example.springbootapp.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomUserRepository extends JpaRepository<CustomUser, String> {
}

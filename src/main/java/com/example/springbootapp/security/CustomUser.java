package com.example.springbootapp.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "custom_user")
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}

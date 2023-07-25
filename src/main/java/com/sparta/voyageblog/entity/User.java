package com.sparta.voyageblog.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "user_username", nullable = false, unique = true, length = 12)
    private String username;

    @Column(name = "user_password", nullable = false, length = 64)
    private String password;

    @Column(name = "user_email", nullable = false, length = 36)
    private String email;

    @Column(name = "user_role", nullable = false, length = 12)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Builder
    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
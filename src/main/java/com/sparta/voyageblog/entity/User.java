package com.sparta.voyageblog.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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
}
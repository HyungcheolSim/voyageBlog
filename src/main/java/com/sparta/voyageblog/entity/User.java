package com.sparta.voyageblog.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private Long id;

    @Column(name = "u_username", nullable = false, unique = true)
    private String username;

    @Column(name = "u_password", nullable = false)
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
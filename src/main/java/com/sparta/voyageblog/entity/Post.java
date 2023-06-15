package com.sparta.voyageblog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="post")
@Getter
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="writer",nullable = false)
    private String writer;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="content",nullable = false)
    private String content;
}

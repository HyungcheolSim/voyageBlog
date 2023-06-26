package com.sparta.voyageblog.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "user")
    private List<Post> postList =new ArrayList<>();

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.role=role;
    }

    //후에 내가 작성한 게시글 목록들 보기?
    public void addPostList(Post post) {
        this.postList.add(post);
        post.setUser(this); //외래키 설정
    }
}
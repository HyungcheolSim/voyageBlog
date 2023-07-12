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
    //양방향 매핑은 user 가 작성한 게시글 목록 보기같은 기능이 필요하면 구현하겠다.
    // User 쪽에서는 Post 를 조회하지 않는 것으로 설정.
    // @OneToMany(mappedBy = "user")
    // private List<Post> postList =new ArrayList<>();

    //마찬가지로 comment 에 있어서도 user 측에서 작성한 댓글목록 보기 기능이 필요하면 그 때 양방향 관계로 바꾸겠다.
    @Builder
    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
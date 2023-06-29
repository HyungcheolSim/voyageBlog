package com.sparta.voyageblog.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "u_username", nullable = false, unique = true, length = 12)
    private String username;

    @Column(name = "u_password", nullable = false, length = 64)
    private String password;

    @Column(name = "u_email", nullable = false, length = 36)
    private String email;

    @Column(name = "u_role", nullable = false, length = 12)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
    //양방향 매핑은 user 가 작성한 게시글 목록 보기같은 기능이 필요하면 구현하겠다.
    // User 쪽에서는 Post 를 조회하지 않는 것으로 설정.
    // @OneToMany(mappedBy = "user")
    // private List<Post> postList =new ArrayList<>();

    //마찬가지로 comment 에 있어서도 user 측에서 작성한 댓글목록 보기 기능이 필요하면 그 때 양방향 관계로 바꾸겠다.
    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
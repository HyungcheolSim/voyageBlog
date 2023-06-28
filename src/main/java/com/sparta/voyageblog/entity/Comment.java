package com.sparta.voyageblog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@IdClass(CommentId.class)
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    //@EmbeddedId 코드
    //@MapsId("postId")
    @ManyToOne
    @JoinColumn(name = "p_id", unique = true, nullable = false, updatable = false)
    public Post post;

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) 사용 불가
    //TODO comment 등록할 때 증가하는 코드를 추가
    @Column(name = "c_id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "c_contents", nullable = false)
    private String contents;

    @Column(name = "c_username", nullable = false, length = 12)
    private String username;
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    private User user;

}

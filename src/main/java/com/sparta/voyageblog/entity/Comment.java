package com.sparta.voyageblog.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "c_contents", nullable = false)
    private String contents;

    @Column(name = "c_username", nullable = false, length = 12)
    private String username;

    //@JsonIgnore
    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", nullable = false, updatable = false)
    public Post post;

    @Builder
    public Comment(Post post, String contents, User user) {
        this.post = post;
        this.contents = contents;
        this.username = user.getUsername();
        this.user = user;
    }

    public void updateComment(String contents) {
        this.contents=contents;
    }
}

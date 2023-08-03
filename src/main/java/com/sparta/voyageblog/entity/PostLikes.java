package com.sparta.voyageblog.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
@Table(name="post_likes")
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_likes_id",nullable = false,updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}

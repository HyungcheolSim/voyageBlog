package com.sparta.voyageblog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "comment_contents", nullable = false)
    private String contents;

    @ColumnDefault("0")
    @Column(name = "comment_likes_count", nullable = false)
    private Integer likesCount;

    //@JsonIgnore
    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    public Post post;

    public void updateComment(String contents) {
        this.contents = contents;
    }

    public void likesCountPlus() {
        this.likesCount++;
    }

    public void likesCountMinus() {
        this.likesCount--;
    }
}

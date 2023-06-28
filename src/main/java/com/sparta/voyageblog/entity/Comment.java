package com.sparta.voyageblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "comment")
//@IdClass(CommentId.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Comment extends Timestamped {


    //@EmbeddedId 코드
    //@JsonIgnore
    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", unique = true, nullable = false, updatable = false)
    public Post post;

    @EmbeddedId
    // @GeneratedValue(strategy = GenerationType.IDENTITY) 사용 불가
    @Column(name = "c_id", unique = true, nullable = false, updatable = false)
    private CommentId id;

    @Column(name = "c_contents", nullable = false)
    private String contents;

    @Column(name = "c_username", nullable = false, length = 12)
    private String username;

    //@JsonIgnore
    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    private User user;

    public Comment(Post post, String contents, User user) {
        this.post = post;
        this.id = new CommentId(post.getId());
        this.contents = contents;
        this.username = user.getUsername();
        this.user = user;
    }

}

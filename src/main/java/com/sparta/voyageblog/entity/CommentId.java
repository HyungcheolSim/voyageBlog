package com.sparta.voyageblog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Random;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CommentId implements Serializable {
    //private Post post;
    //@EmbeddedId 코드
    private Long postId;
    @Column(name = "c_id")
    private Long id;

    public CommentId(Long postId) {
        this.postId = postId;
        this.id = new Random().nextLong(1000000L);
        //Auto increment 를 못쓰기 때문에
    }
}

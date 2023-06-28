package com.sparta.voyageblog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor

@Embeddable
public class CommentId implements Serializable {
    //private Post post;
    //@EmbeddedId 코드
    private Long postId;
    @Column(name="c_id")
    private Long id=1234L;

    public CommentId(Long postId){
        this.postId=postId;
        this.id++;
    }
}

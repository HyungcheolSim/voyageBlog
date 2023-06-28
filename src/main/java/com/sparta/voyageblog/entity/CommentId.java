package com.sparta.voyageblog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode

//@Embeddable
public class CommentId implements Serializable {
    private Post post;
    //@EmbeddedId 코드
    //private Long postId;
    //@Column(name="c_id")
    private Long id;
}

package com.sparta.voyageblog.dto;

import com.sparta.voyageblog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private String contents;
    private String username;
    private Integer likesCount;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.contents=comment.getContents();
        this.username=comment.getUser().getUsername();
        this.likesCount=comment.getLikesCount();
        this.createdAt=comment.getCreatedAt();
    }
}

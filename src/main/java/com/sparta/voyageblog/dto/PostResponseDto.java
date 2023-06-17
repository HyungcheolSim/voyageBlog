package com.sparta.voyageblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.voyageblog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private Boolean success;
    private Long id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post){
        this.id=post.getId();
        this.title=post.getTitle();
        this.writer=post.getWriter();
        this.contents=post.getContents();
        this.createdAt=post.getCreatedAt();
    }

    public PostResponseDto(boolean success) {
        this.success=success;
    }
}

package com.sparta.voyageblog.dto;

import com.sparta.voyageblog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
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
}

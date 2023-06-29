package com.sparta.voyageblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class PostResponseDto {
    //private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    //private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList=new ArrayList<>();

    public PostResponseDto(Post post){
        //this.id=post.getId();
        this.title=post.getTitle();
        this.username=post.getUsername();
        this.contents=post.getContents();
        this.createdAt=post.getCreatedAt();
        //this.modifiedAt=post.getModifiedAt();
        this.commentList.addAll(post.getCommentList().stream().sorted(Comparator.comparing(Comment::getCreatedAt).reversed()).map(CommentResponseDto::new).toList());
        //생성일 내림차순 정렬, 추가될 땐 맨 앞에 추가되로록함.

    }
    
}

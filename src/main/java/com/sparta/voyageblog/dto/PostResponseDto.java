package com.sparta.voyageblog.dto;

import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class PostResponseDto {
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private Integer likesCount;
    private List<CommentResponseDto> commentList=new ArrayList<>();

    public PostResponseDto(Post post){
        this.title=post.getTitle();
        this.username=post.getUser().getUsername();
        this.contents=post.getContents();
        this.createdAt=post.getCreatedAt();
        this.likesCount=post.getLikesCount();
        this.commentList.addAll(post.getCommentList().stream().sorted(Comparator.comparing(Comment::getCreatedAt).reversed()).map(CommentResponseDto::new).toList());
        //생성일 내림차순 정렬, 추가될 땐 맨 앞에 추가되로록함.
    }
}

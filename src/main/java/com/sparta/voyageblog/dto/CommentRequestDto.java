package com.sparta.voyageblog.dto;

import com.sparta.voyageblog.entity.CommentId;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    //private CommentId id;
    private Long postId;
    private String contents;


}

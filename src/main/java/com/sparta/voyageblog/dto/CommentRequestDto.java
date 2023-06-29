package com.sparta.voyageblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.voyageblog.entity.CommentId;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentRequestDto {
    private Long postId;
    private String contents;


}

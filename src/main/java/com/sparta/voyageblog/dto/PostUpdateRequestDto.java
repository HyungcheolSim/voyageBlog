package com.sparta.voyageblog.dto;

import lombok.Getter;


@Getter
public class PostUpdateRequestDto {
    private String password;

    private Long id;
    private String title;
    private String writer;
    private String contents;
}

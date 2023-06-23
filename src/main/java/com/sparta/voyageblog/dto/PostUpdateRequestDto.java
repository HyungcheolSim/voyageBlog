package com.sparta.voyageblog.dto;

import lombok.Getter;


@Getter
public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
}

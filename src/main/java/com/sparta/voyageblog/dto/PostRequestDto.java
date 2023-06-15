package com.sparta.voyageblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String writer;
    private String password;
    private String contents;
}

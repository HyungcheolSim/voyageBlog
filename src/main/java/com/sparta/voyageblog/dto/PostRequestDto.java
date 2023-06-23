package com.sparta.voyageblog.dto;

import com.sparta.voyageblog.entity.User;
import lombok.Getter;


@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private String contents;
    private User user;
}

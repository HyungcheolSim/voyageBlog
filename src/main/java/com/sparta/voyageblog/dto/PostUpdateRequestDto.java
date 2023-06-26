package com.sparta.voyageblog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class PostUpdateRequestDto {
    @NotBlank
    private Long id;
    private String title;
    private String contents;
}

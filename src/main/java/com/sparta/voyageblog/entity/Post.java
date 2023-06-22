package com.sparta.voyageblog.entity;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id")
    private Long id;
    @Column(name="p_title",nullable = false)
    private String title;
    @Column(name="p_username",nullable = false)
    private String username;
    @Column(name="p_contents",nullable = false)
    private String contents;


    public Post(PostRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.username=requestDto.getWriter();
        this.contents=requestDto.getContents();
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.username=requestDto.getWriter();
        this.contents=requestDto.getContents();
    }
}

package com.sparta.voyageblog.entity;

import com.sparta.voyageblog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
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
    private Long id;
    @Column(name="title",nullable = false)
    private String title;
    @Column(name="writer",nullable = false)
    private String writer;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="contents",nullable = false)
    private String contents;


    public Post(PostRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.writer=requestDto.getWriter();
        this.password=requestDto.getPassword();
        this.contents=requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.writer=requestDto.getWriter();
        this.password=requestDto.getPassword();
        this.contents=requestDto.getContents();
    }
}

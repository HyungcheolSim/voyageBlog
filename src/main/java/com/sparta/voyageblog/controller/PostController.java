package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

}

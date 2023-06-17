package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
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

    @GetMapping("/posts/{id}")
    public PostResponseDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PutMapping("/posts")
    public PostResponseDto updatePost(@RequestBody PostUpdateRequestDto updateRequestDto){
        return postService.updatePost(updateRequestDto);
    }
    @DeleteMapping("/posts/{id}")
    public PostResponseDto deletePost(@PathVariable Long id,@RequestBody PostRequestDto postRequestDto){
        return new PostResponseDto(postService.deletePost(id,postRequestDto.getPassword()));
    }
}

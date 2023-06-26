package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.GeneralResponseDto;
import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import com.sparta.voyageblog.security.UserDetailsImpl;
import com.sparta.voyageblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    //전체 게시글 조회

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }
    //게시글 등록
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(postService.createPost(requestDto, userDetails.getUser()));
    }

    //특정 게시글 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }
    //내가 작성한 특정 게시글 수정
    @PutMapping("/posts")
    public PostResponseDto updatePost(@RequestBody PostUpdateRequestDto updateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(updateRequestDto,userDetails.getUser());
    }
    //내가 작성한 특정 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<GeneralResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getUsername());
        return ResponseEntity.ok(new GeneralResponseDto("삭제 완료", HttpStatus.OK));
    }
}

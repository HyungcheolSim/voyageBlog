package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.*;
import com.sparta.voyageblog.security.UserDetailsImpl;
import com.sparta.voyageblog.service.PostLikesService;
import com.sparta.voyageblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final PostLikesService postLikesService;

    //전체 게시글 조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        return ResponseEntity.ok().body(postService.getPosts());
    }

    //게시글 등록
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(requestDto, userDetails.getUser()));
    }

    //특정 게시글 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    //내가 작성한 특정 게시글 수정
    @PutMapping("/posts")
    public PostResponseDto updatePost(@RequestBody PostUpdateRequestDto updateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(updateRequestDto, userDetails.getUser());
    }

    //내가 작성한 특정 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(id, userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("삭제 완료", HttpStatus.OK));
    }

    //게시글 좋아요
    @PostMapping("/posts/{id}/likes")
    public ResponseEntity<ApiResponseDto> insertPostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postLikesService.insertPostLikes(id,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto("좋아요 등록 완료", HttpStatus.CREATED));
    }

    //게시글 좋아요 취소
    @DeleteMapping("/posts/likes/{id}")     //post likes id
    public ResponseEntity<ApiResponseDto> deletePostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postLikesService.deletePostLikes(id, userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("좋아요 취소 완료", HttpStatus.OK));
    }
}

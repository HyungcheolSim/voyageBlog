package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.ApiResponseDto;
import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import com.sparta.voyageblog.security.UserDetailsImpl;
import com.sparta.voyageblog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    //전체 게시글 조회
    @GetMapping("/posts")
    public ResponseEntity<ApiResponseDto> getPosts() {
        return ResponseEntity.ok().body(
                new ApiResponseDto("특정 게시글 조회 성공",
                        HttpStatus.ACCEPTED,
                        postService.getPosts()
                ));
    }

    //게시글 등록
    @PostMapping("/posts")
    public ResponseEntity<ApiResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto("게시글 등록 성공",
                        HttpStatus.CREATED,
                        postService.createPost(requestDto, userDetails.getUser())
                ));
    }

    //특정 게시글 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                new ApiResponseDto("특정 게시글 조회 성공",
                        HttpStatus.ACCEPTED,
                        postService.getPostById(id)
                ));
    }

    //내가 작성한 특정 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto updateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(
                new ApiResponseDto("게시글 수정 성공",
                        HttpStatus.ACCEPTED,
                        postService.updatePost(postService.findPost(id), updateRequestDto, userDetails.getUser())
                ));

    }

    //내가 작성한 특정 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postService.findPost(id), userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("게시글 삭제 완료", HttpStatus.OK));
    }

    //게시글 좋아요
    @PostMapping("/posts/{id}/likes")
    public ResponseEntity<ApiResponseDto> insertPostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.likePost(id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("게시글 좋아요 성공", HttpStatus.ACCEPTED));
    }

    //게시글 좋아요 취소
    @DeleteMapping("/posts/{id}/likes")
    public ResponseEntity<ApiResponseDto> deletePostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deleteLikePost(id, userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("좋아요 취소 완료", HttpStatus.OK));
    }
}

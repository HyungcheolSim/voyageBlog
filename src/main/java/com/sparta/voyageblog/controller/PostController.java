package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.aop.Timer;
import com.sparta.voyageblog.dto.ApiResponseDto;
import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import com.sparta.voyageblog.security.UserDetailsImpl;
import com.sparta.voyageblog.service.PostLikeServiceImpl;
import com.sparta.voyageblog.service.PostServiceImpl;
import com.sparta.voyageblog.service.factory.PostLikeServiceFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostServiceImpl postService;
    private final PostLikeServiceImpl postLikeService;

    public PostController(PostServiceImpl postService,PostLikeServiceFactory factory){
        this.postService=postService;
        this.postLikeService= (PostLikeServiceImpl) factory.create();
    }

    //전체 게시글 조회
    @Timer
    @GetMapping("/posts")
    public ResponseEntity<ApiResponseDto> getPosts() {
        return ResponseEntity.ok().body(
                new ApiResponseDto("특정 게시글 조회 성공",
                        HttpStatus.OK,
                        postService.getPosts()
                ));
    }

    //게시글 등록
    @Timer
    @PostMapping("/posts")
    public ResponseEntity<ApiResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto("게시글 등록 성공",
                        HttpStatus.CREATED,
                        postService.createPost(requestDto, userDetails.getUser())
                ));
    }

    //특정 게시글 조회
    @Timer
    @GetMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok().body(
                new ApiResponseDto("특정 게시글 조회 성공",
                        HttpStatus.ACCEPTED,
                        postService.getPostById(id)
                ));
    }

    //내가 작성한 특정 게시글 수정
    @Timer
    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto updateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(
                new ApiResponseDto("게시글 수정 성공",
                        HttpStatus.ACCEPTED,
                        postService.updatePost(postService.findPost(id), updateRequestDto, userDetails.getUser())
                ));

    }

    //내가 작성한 특정 게시글 삭제
    @Timer
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postService.findPost(id), userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("게시글 삭제 완료", HttpStatus.OK));
    }

    //게시글 좋아요
    @Timer
    @PostMapping("/posts/{id}/likes")
    public ResponseEntity<ApiResponseDto> insertPostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postLikeService.like(postService.findPost(id), userDetails.getUser());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("게시글 좋아요 성공", HttpStatus.ACCEPTED));
    }

    //게시글 좋아요 취소
    @Timer
    @DeleteMapping("/posts/{id}/likes")
    public ResponseEntity<ApiResponseDto> deletePostLikes(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postLikeService.deleteLike(postService.findPost(id), userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("좋아요 취소 완료", HttpStatus.OK));
    }
}

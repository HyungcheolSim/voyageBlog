package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.ApiResponseDto;
import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.security.UserDetailsImpl;
import com.sparta.voyageblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    //댓글 등록

    @PostMapping("/comments")
    public ResponseEntity<ApiResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto("게시글 등록 성공",
                        HttpStatus.CREATED,
                        commentService.createComment(commentRequestDto, userDetails.getUser())
                ));
    }

    //댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<ApiResponseDto> updateComment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                new ApiResponseDto("댓글 수정 완료",
                        HttpStatus.ACCEPTED,
                        commentService.updateComment(commentService.findComment(id),commentRequestDto,userDetails.getUser())
                ));
    }

    //댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentService.findComment(id),userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("댓글 삭제 완료", HttpStatus.OK));
    }

    //댓글 좋아요
    @PostMapping("/comments/{id}/likes")
    public ResponseEntity<ApiResponseDto> likeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.likeComment(id,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDto("좋아요 등록 완료", HttpStatus.CREATED));
    }

    //댓글 좋아요 취소
    @DeleteMapping("/comments/{id}/likes")     //comment likes id
    public ResponseEntity<ApiResponseDto> deleteLikeComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteLikeComment(id, userDetails.getUser());
        return ResponseEntity.ok(new ApiResponseDto("좋아요 취소 완료", HttpStatus.OK));
    }
}

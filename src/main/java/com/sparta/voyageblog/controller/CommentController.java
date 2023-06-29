package com.sparta.voyageblog.controller;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.dto.GeneralResponseDto;
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
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok().body(commentService.createComment(commentRequestDto,userDetails.getUser()));
    }

    //댓글 수정
    @PutMapping("/comments")
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.ok().body(commentService.updateComment(commentRequestDto,userDetails.getUser()));
    }

    //댓글 삭제
    @DeleteMapping("/comments")
    public ResponseEntity<GeneralResponseDto> deleteComment(@RequestBody CommentRequestDto commentRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deleteComment(commentRequestDto,userDetails.getUser());
        return ResponseEntity.ok(new GeneralResponseDto("댓글 삭제 완료", HttpStatus.OK));
    }
}

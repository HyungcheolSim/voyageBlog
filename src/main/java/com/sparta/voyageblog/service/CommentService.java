package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.entity.*;
import com.sparta.voyageblog.repository.CommentRepository;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {

        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = Comment.builder()
                .post(post)
                .contents(requestDto.getContents())
                .user(user)
                .build();
        post.addCommentList(comment);

        log.info("댓글 " + comment.getContents() + " 등록");
        return new CommentResponseDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findByPost_IdAndId(commentRequestDto.getPostId(), commentRequestDto.getCommentId()).orElseThrow(() -> new IllegalArgumentException("댓글이나 게시글이 존재하지 않습니다."));
        if (!(comment.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN))) { //comment 내 user의 id와 현재 로그인한 user의 id 비교해서 다르면 예외
            throw new IllegalArgumentException("이 댓글을 수정할 권한이 없습니다.");
        }
        comment.updateComment(commentRequestDto.getContents());
        log.info("댓글 수정 실행");
        //comment list 에서의 수정도 있어야하겠네..
        //해당 post 의 comment list 에서 commentId가 일치하는 comment 를 찾아서 수정->stream 잘쓰고싶다..
        for (Comment com : post.getCommentList()) {
            if (com.getId().equals(comment.getId())) {
                com.updateComment(commentRequestDto.getContents());
            }
        }
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findByPost_IdAndId(commentRequestDto.getPostId(), commentRequestDto.getCommentId()).orElseThrow(() -> new IllegalArgumentException("댓글이나 게시글이 존재하지 않습니다."));
        if (!(comment.getUser().getId().equals(user.getId()) || user.getRole().equals(UserRoleEnum.ADMIN))) {
            throw new IllegalArgumentException("이 댓글을 삭제할 권한이 없습니다.");
        }
        commentRepository.delete(comment);
    }
}

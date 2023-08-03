package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.CommentRepository;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
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

        post.getCommentList().add(comment);

        log.info("댓글 " + comment.getContents() + " 등록");
        return new CommentResponseDto(commentRepository.save(comment));
    }

    @Transactional
    public CommentResponseDto updateComment(Comment comment, CommentRequestDto commentRequestDto, User user) {

        comment.updateComment(commentRequestDto.getContents());

        Post post = comment.getPost();
        for (Comment com : post.getCommentList()) {
            if (com.getId().equals(comment.getId())) {
                com.updateComment(commentRequestDto.getContents());
            }
        }
        log.info("댓글 수정 완료");
        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Comment comment, User user) {

        commentRepository.delete(comment);
        Post post = comment.getPost();
        post.getCommentList().remove(comment);
        log.info("댓글 삭제 완료");
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }
}

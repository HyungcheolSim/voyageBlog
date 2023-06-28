package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.CommentRepository;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        //양방향일 때
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment(post, requestDto.getContents(), user);
        post.addCommentList(comment);
        return new CommentResponseDto(commentRepository.save(comment));
    }
}

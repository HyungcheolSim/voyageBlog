package com.sparta.voyageblog.service;

import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.CommentLikes;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.CommentLikesRepository;
import com.sparta.voyageblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void insertCommentLikes(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("게시글이 없어요잉"));
        if (commentLikesRepository.existsByCommentAndUser(comment, user)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 상태입니다.");
        }
        CommentLikes commentLikes=CommentLikes.builder()
                .comment(comment)
                .user(user)
                .build();
        commentLikesRepository.save(commentLikes);
        comment.likesCountPlus();
    }

    @Transactional
    public void deleteCommentLikes(Long commentId, User user) {
        CommentLikes commentLikes=commentLikesRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("좋아요를 누른 상태가 아닙니다."));
        if(!Objects.equals(commentLikes.getUser().getId(), user.getId())){
            throw new IllegalArgumentException("본인이 누른 좋아요가 아닙니다.");
        }
        commentLikesRepository.delete(commentLikes);
        Comment comment=commentLikes.getComment();
        comment.likesCountMinus();
    }
}

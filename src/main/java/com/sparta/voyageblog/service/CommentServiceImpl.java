package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.CommentLikes;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.CommentLikesRepository;
import com.sparta.voyageblog.repository.CommentRepository;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService, LikeService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentLikesRepository commentLikesRepository;

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

    @Transactional
    public void like(Long commentId, User user) {
        Comment comment = findComment(commentId);
        if (commentLikesRepository.existsByCommentAndUser(comment, user)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 상태입니다.");
        } else {
            CommentLikes commentLikes = CommentLikes.builder().comment(comment).user(user).build();
            commentLikesRepository.save(commentLikes);
            comment.likesCountPlus();
        }
    }

    @Transactional
    public void deleteLike(Long commentId, User user) {
        Comment comment = findComment(commentId);
        Optional<CommentLikes> commentLikes = commentLikesRepository.findByUserAndComment(comment, user);

        if (commentLikes.isPresent()) {
            commentLikesRepository.delete(commentLikes.get());
            comment.likesCountMinus();
        } else {
            throw new IllegalArgumentException("해당 댓글에 취소할 좋아요가 없습니다.");
        }
    }
}

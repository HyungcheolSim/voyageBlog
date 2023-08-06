package com.sparta.voyageblog.service;

import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.CommentLikes;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.CommentLikesRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements LikeService{
  private final CommentLikesRepository commentLikesRepository;
  @Transactional
  public void like(Object o, User user) {
    if(o==null)
      throw new NullPointerException("댓글이 존재하지 않습니다.");

    Comment comment=(Comment) o;
    if (commentLikesRepository.existsByCommentAndUser(comment, user)) {
      throw new IllegalArgumentException("이미 좋아요를 누른 상태입니다.");
    } else {
      CommentLikes commentLikes = CommentLikes.builder().comment(comment).user(user).build();
      commentLikesRepository.save(commentLikes);
      comment.likesCountPlus();
    }
  }

  @Transactional
  public void deleteLike(Object o, User user) {
    if(o==null)
      throw new NullPointerException("댓글이 존재하지 않습니다.");

    Comment comment=(Comment) o;
    Optional<CommentLikes> commentLikes = commentLikesRepository.findByCommentAndUser(comment, user);

    if (commentLikes.isPresent()) {
      commentLikesRepository.delete(commentLikes.get());
      comment.likesCountMinus();
    } else {
      throw new IllegalArgumentException("해당 댓글에 취소할 좋아요가 없습니다.");
    }
  }
}

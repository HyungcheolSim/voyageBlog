package com.sparta.voyageblog.service;

import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.PostLikes;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.PostLikesRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements LikeService{

  private final PostLikesRepository postLikesRepository;
  @Override
  @Transactional
  public void like(Object o, User user) {
    if(o==null)
      throw new NullPointerException("게시글이 존재하지 않습니다.");
    Post post = (Post)o;
    if (!postLikesRepository.existsByPostAndUser(post, user)) {
      PostLikes postLikes = PostLikes.builder()
          .post(post)
          .user(user)
          .build();
      postLikesRepository.save(postLikes);
      post.likesCountPlus();
    } else {
      throw new DuplicateRequestException("이미 좋아요를 누른 상태입니다.");
    }
  }


  @Override
  @Transactional
  public void deleteLike(Object o, User user) {
    if(o==null)
      throw new NullPointerException("게시글이 존재하지 않습니다.");
    Post post = (Post)o;
    PostLikes postLikes = postLikesRepository.findByUserAndPost(user, post).orElseThrow(() -> new IllegalArgumentException("본인이 누른 좋아요가 아닙니다."));
    postLikesRepository.delete(postLikes);
    post.likesCountMinus();
  }

}

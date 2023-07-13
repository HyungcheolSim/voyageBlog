package com.sparta.voyageblog.service;

import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.PostLikes;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.PostLikesRepository;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostLikesService {
    private final PostLikesRepository postLikesRepository;
    private final PostRepository postRepository;


    @Transactional
    public void insertPostLikes(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 없어요잉"));
        if (postLikesRepository.existsByPostAndUser(post, user)) {
            throw new IllegalArgumentException("이미 좋아요를 누른 상태입니다.");
        }
        PostLikes postLikes=PostLikes.builder()
                .post(post)
                .user(user)
                .build();
        postLikesRepository.save(postLikes);
        post.likesCountPlus();
    }

    @Transactional
    public void deletePostLikes(Long postId, User user) {
        PostLikes postLikes=postLikesRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("좋아요를 누른 상태가 아닙니다."));
        if(!Objects.equals(postLikes.getUser().getId(), user.getId())){
            throw new IllegalArgumentException("본인이 누른 좋아요가 아닙니다.");
        }
        postLikesRepository.delete(postLikes);
        Post post=postLikes.getPost();
        post.likesCountMinus();
    }
}

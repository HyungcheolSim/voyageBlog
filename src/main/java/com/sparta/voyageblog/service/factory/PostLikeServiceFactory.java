package com.sparta.voyageblog.service.factory;

import com.sparta.voyageblog.repository.PostLikesRepository;
import com.sparta.voyageblog.repository.PostRepository;
import com.sparta.voyageblog.service.LikeService;
import com.sparta.voyageblog.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostLikeServiceFactory extends LikeServiceFactory{
    private final PostRepository postRepository;
    private final PostLikesRepository postLikesRepository;
    @Override
    protected LikeService createLikeService() {
        return new PostServiceImpl(postRepository,postLikesRepository);
    }
}

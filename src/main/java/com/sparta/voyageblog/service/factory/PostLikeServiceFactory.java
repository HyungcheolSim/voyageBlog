package com.sparta.voyageblog.service.factory;

import com.sparta.voyageblog.repository.PostLikesRepository;
import com.sparta.voyageblog.repository.PostRepository;
import com.sparta.voyageblog.service.LikeService;
import com.sparta.voyageblog.service.PostLikeServiceImpl;
import com.sparta.voyageblog.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostLikeServiceFactory extends LikeServiceFactory{

    private final PostLikesRepository postLikesRepository;
    @Override
    protected LikeService createLikeService() {
        return new PostLikeServiceImpl(postLikesRepository);
    }
}

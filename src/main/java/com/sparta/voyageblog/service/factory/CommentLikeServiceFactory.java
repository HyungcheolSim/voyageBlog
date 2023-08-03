package com.sparta.voyageblog.service.factory;

import com.sparta.voyageblog.repository.CommentLikesRepository;
import com.sparta.voyageblog.repository.CommentRepository;
import com.sparta.voyageblog.service.CommentLikeServiceImpl;
import com.sparta.voyageblog.service.LikeService;
import com.sparta.voyageblog.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentLikeServiceFactory extends LikeServiceFactory{
    private final CommentLikesRepository commentLikesRepository;
    @Override
    protected LikeService createLikeService() {
        return new CommentLikeServiceImpl(commentLikesRepository);
    }
}

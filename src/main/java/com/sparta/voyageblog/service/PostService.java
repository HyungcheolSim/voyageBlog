package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post= new Post(requestDto);
        Post savePost= postRepository.save(post);
        return new PostResponseDto(savePost);
    }



}

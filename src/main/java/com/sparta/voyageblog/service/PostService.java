package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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


    public PostResponseDto getPostById(Long id) {
         Post findPost=findPost(id);
         return new PostResponseDto(findPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long id,String password,PostRequestDto requestDto) {
        Post post=findPost(id);

        if(checkPassword(post.getPassword(),password)){//비밀번호 확인되면
            post.update(requestDto); //dirty check로 update된다.
        }
        return new PostResponseDto(findPost(id));
    }
    @Transactional
    public String deletePost(Long id) {
        Post post=findPost(id);
        postRepository.delete(post);
        return "삭제 성공!";
    }



    //private methods
    private Boolean checkPassword(String input,String password) {
        return Objects.equals(input, password);
    }

    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}

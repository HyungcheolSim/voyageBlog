package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
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

    //Post 생성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post= new Post(requestDto);
        Post savePost= postRepository.save(post);
        return new PostResponseDto(savePost);
    }
    
    //특정 id의 post 조회
    public PostResponseDto getPostById(Long id) {
         Post findPost=findPost(id);
         return new PostResponseDto(findPost);
    }

    //Post 수정
    @Transactional
    public PostResponseDto updatePost(PostUpdateRequestDto requestDto) {
        Post post=findPost(requestDto.getId()); //존재찾기

        if(checkPassword(post.getPassword(),requestDto.getPassword())){//비밀번호 확인되면
            post.update(requestDto); //dirty check 로 update 된다.
        }
        return new PostResponseDto(findPost(requestDto.getId())); //다시 불러와서 조회
    }
    @Transactional
    public String deletePost(Long id) {
        Post post=findPost(id);
        postRepository.delete(post);
        return "true";
    }

    //private methods Service 내에서만 사용되는 메서드!
    //비밀번호 일치 여부 확인 Boolean
    private Boolean checkPassword(String input,String password) {
        return Objects.equals(input, password);
    }

    //특정 id를 가지는 Post 를 찾는 method, getPostById와 다른점은 리턴값이 post 객체라는 점
    private Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
    /*
    {
        "timestamp": "2023-06-16T02:59:25.823+00:00",
        "status": 500,
        "error": "Internal Server Error",
        "path": "/api/posts/7"
    }
     */
}

package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.entity.UserRoleEnum;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : postList)
            postResponseDtos.add(new PostResponseDto(post));
        return postResponseDtos;
    }

    //Post 생성
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        return new PostResponseDto(postRepository.save(new Post(requestDto, user)));
    }

    //특정 id의 post 조회
    public PostResponseDto getPostById(Long id) {
        return new PostResponseDto(findPost(id));
    }

    //Post 수정
    @Transactional
    public PostResponseDto updatePost(PostUpdateRequestDto requestDto, User user) {
        Post post = findPost(requestDto.getId()); //기존 것
        if (!(post.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN))) { //기존 게시글의 username 과 현재 로그인한 username 비교해서 다르면 예외
            throw new IllegalArgumentException("게시글 수정할 권한이 없습니다.");
        }
        post.updatePost(requestDto);
        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long id, User user) {
        Post post = findPost(id);
        if (!(post.getUsername().equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN))) { //기존 게시글의 username 과 현재 로그인한 username 비교해서 다르면 예외
            throw new IllegalArgumentException("게시글 삭제할 권한이 없습니다.");
        }
        postRepository.delete(post);
    }

    //private methods Service 내에서만 사용되는 메서드!
    //특정 id를 가지는 Post 를 찾는 method, getPostById와 다른점은 리턴값이 post 객체라는 점
    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}

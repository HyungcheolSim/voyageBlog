package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    //전체 post 조회
    @Override
    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    //Post 생성
    @Override
    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .user(user)
                .likesCount(0)
                .build();
        return new PostResponseDto(postRepository.save(post));
    }

    //특정 id의 post 조회
    @Override
    public PostResponseDto getPostById(Long id) {
        return new PostResponseDto(findPost(id));
    }

    //Post 수정
    @Transactional
    @Override
    public PostResponseDto updatePost(Post post, PostUpdateRequestDto requestDto, User user) {
        post.updatePost(requestDto);
        return new PostResponseDto(post);
    }

    @Transactional
    @Override
    public void deletePost(Post post, User user) {
        postRepository.delete(post);
    }

    //특정 id를 가지는 Post 를 찾는 method, getPostById와 다른점은 리턴값이 post 객체라는 점
    @Override
    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}

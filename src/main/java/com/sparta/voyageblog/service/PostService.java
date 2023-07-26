package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostResponseDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.User;

import java.util.List;

public interface PostService {

    /**
     * 전체 게시글 조회
     *
     * @return 전체 게시글 목록
     */
    List<PostResponseDto> getPosts();

    /**
     * 게시글 등록
     *
     * @param requestDto 게시글 등록에 필요한 데이터 (title, contents)
     * @param user       현재 로그인되어 있는 유저
     * @return 생성된 게시글
     */
    PostResponseDto createPost(PostRequestDto requestDto, User user);

    /**
     * 특정 id의 게시글 조회
     *
     * @param id 게시글 식별 번호(post_id)
     * @return post_id에 해당하는 게시글
     */
    PostResponseDto getPostById(Long id);

    /**
     * 게시글 수정
     *
     * @param post       수정할 게시글
     * @param requestDto 수정할 데이터(title, contents)
     * @param user       현재 로그인되어 있는 유저
     * @return 수정된 post
     */
    PostResponseDto updatePost(Post post, PostUpdateRequestDto requestDto, User user);

    /**
     * 게시글 삭제
     *
     * @param post 삭제할 게시글
     * @param user 현재 로그인되어 있는 유저
     */
    void deletePost(Post post, User user);

    /**
     * 특정 id를 가지는 Post 를 찾는 method, getPostById와 다른점은 리턴값이 post 객체라는 점
     *
     * @param id 게시글 식별 번호(post_id)
     * @return id에 해당하는 게시글
     */
    Post findPost(Long id);

}

package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.User;

public interface CommentService {
    /**
     * 댓글 등록
     *
     * @param requestDto 댓글 등록에 필요한 데이터 (title, contents)
     * @param user       현재 로그인되어 있는 유저
     * @return 생성된 댓글
     */
    CommentResponseDto createComment(CommentRequestDto requestDto, User user);

    /**
     * 댓글 수정
     *
     * @param comment       수정할 댓글
     * @param commentRequestDto 수정할 데이터(postId, contents)
     * @param user       현재 로그인되어 있는 유저
     * @return 수정된 post
     */
    CommentResponseDto updateComment(Comment comment, CommentRequestDto commentRequestDto, User user);

    /**
     * 댓글 삭제
     *
     * @param comment 삭제할 댓글
     * @param user 현재 로그인되어 있는 유저
     */
    void deleteComment(Comment comment, User user);

    /**
     * 특정 id를 가지는 댓글 조회
     *
     * @param id 댓글 식별 번호(post_id)
     * @return id에 해당하는 댓글
     */
    Comment findComment(Long id);
}

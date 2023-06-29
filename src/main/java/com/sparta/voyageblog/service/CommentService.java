package com.sparta.voyageblog.service;

import com.sparta.voyageblog.dto.CommentRequestDto;
import com.sparta.voyageblog.dto.CommentResponseDto;
import com.sparta.voyageblog.entity.*;
import com.sparta.voyageblog.repository.CommentRepository;
import com.sparta.voyageblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        //양방향일 때
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Comment comment = new Comment(post, requestDto.getContents(), user);
        post.addCommentList(comment);
        return new CommentResponseDto(commentRepository.save(comment));
    }
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        CommentId commentId=new CommentId(commentRequestDto.getPostId(),id);
        Comment comment=commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        if (!comment.getUsername().equals(user.getUsername())) { //기존 게시글의 username 과 현재 로그인한 username 비교해서 다르면 예외
            if(!user.getRole().equals(UserRoleEnum.ADMIN)) {
                throw new SecurityException("이 게시글을 삭제할 권한이 없습니다.");
            }
            throw new IllegalArgumentException("회원님이 작성한 게시글이 아닙니다. 수정할 수 없습니다.");
        }
        comment.update(commentRequestDto.getContents());
        log.info("댓글 수정 실행");
        //commentlist에서의 수정도 있어야하겠네..
        //해당 post의 commentlist에서 commentId가 일치하는 comment를 찾아서 수정->stream 잘쓰고싶다..
        Post post=postRepository.findById(commentRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        for(Comment com:post.getCommentList()){
            if(com.getId().equals(commentId)){
                com.setContents(comment.getContents());
            }
        }
        return new CommentResponseDto(comment);
    }
}

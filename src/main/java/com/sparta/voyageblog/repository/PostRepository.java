package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    //내림차순 정렬
    List<Post> findAllByOrderByCreatedAtDesc();
    //선택한 게시글 조회
    //등록
}

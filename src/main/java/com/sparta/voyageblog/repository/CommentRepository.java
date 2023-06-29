package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByPost_IdAndId(Long postId,Long commentId);

}

package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.CommentId;
import com.sparta.voyageblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {
    List<Comment> findAllByIdOrderByCreatedAtDesc(CommentId commentId);
    List<Comment> findCommentsByPostOrderByCreatedAtDesc(Post post);
}

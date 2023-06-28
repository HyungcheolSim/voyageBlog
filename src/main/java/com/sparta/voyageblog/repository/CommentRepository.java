package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Comment;
import com.sparta.voyageblog.entity.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {
}

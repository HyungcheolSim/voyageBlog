package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
     Boolean existsByCommentAndUser(Comment comment, User requestUser);
     Optional<CommentLikes> findByUserAndComment(Comment comment,User user);
}

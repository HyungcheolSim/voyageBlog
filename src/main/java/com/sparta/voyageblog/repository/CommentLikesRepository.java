package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
     Boolean existsByCommentAndUser(Comment comment, User requestUser);
}

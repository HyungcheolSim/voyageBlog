package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Post;
import com.sparta.voyageblog.entity.PostLikes;
import com.sparta.voyageblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikesRepository extends JpaRepository<PostLikes,Long> {
     Boolean existsByPostAndUser(Post post, User requestUser);
}

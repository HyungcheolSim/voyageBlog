package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}

package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Post.class, idClass = Long.class)
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryQuery {

  //내림차순 정렬
  List<Post> findAllByOrderByCreatedAtDesc();


}

package com.sparta.voyageblog.repository;

import com.sparta.voyageblog.entity.Post;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryQuery {

  List<Post> searchContents(String keyword, Pageable pageable);
}

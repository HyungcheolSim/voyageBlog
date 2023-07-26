package com.sparta.voyageblog.service;

import com.sparta.voyageblog.entity.User;

public interface LikeService {

    void like(Long id, User user);

    void deleteLike(Long id, User user);
}

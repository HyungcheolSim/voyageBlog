package com.sparta.voyageblog.service;

import com.sparta.voyageblog.entity.User;

public interface LikeService {

    void like(Object o, User user);

    void deleteLike(Object o, User user);
}

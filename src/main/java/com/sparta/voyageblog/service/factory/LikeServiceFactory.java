package com.sparta.voyageblog.service.factory;

import com.sparta.voyageblog.entity.User;
import com.sparta.voyageblog.service.LikeService;

public abstract class LikeServiceFactory {

     public LikeService create(Long id,User user){
         LikeService likeService=createLikeService();
         likeService.like(id,user);
         likeService.deleteLike(id,user);
         return likeService;
     }

    protected abstract LikeService createLikeService();
}

package com.sparta.voyageblog.service.factory;

import com.sparta.voyageblog.service.LikeService;

public abstract class LikeServiceFactory {

     public LikeService create(){
         LikeService likeService=createLikeService();
         return likeService;
     }

    protected abstract LikeService createLikeService();
}

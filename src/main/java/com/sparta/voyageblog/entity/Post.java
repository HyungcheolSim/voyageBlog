package com.sparta.voyageblog.entity;

import com.sparta.voyageblog.dto.PostRequestDto;
import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, updatable = false, nullable = false)
    private Long id;
    @Column(name = "post_title", nullable = false, length = 32)
    private String title;
    @Column(name = "post_username", nullable = false, length = 12)
    private String username;
    @Column(name = "post_contents", nullable = false,columnDefinition = "text")
    //255 이상의 문자를 저장하고 싶을 때 사용 columnDefinition = "text" 65535글자 가능
    private String contents;

    @ColumnDefault("0")
    @Column(name="post_likes_count",nullable = false)
    private Integer likesCount;

    //default @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //@JsonIgnore
    //@JsonBackReference
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.username = user.getUsername();
        this.contents = requestDto.getContents();
        this.user = user;
        this.likesCount=0;
        //user.getPostList().add(this); //양방향 연관관계 처리
    }

    public void updatePost(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void addCommentList(Comment comment) {
        this.commentList.add(0,comment);
    }

    public void likesCountPlus() {
        this.likesCount++;
    }
    public void likesCountMinus() {
        this.likesCount--;
    }
}

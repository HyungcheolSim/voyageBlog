package com.sparta.voyageblog.entity;

import com.sparta.voyageblog.dto.PostUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, updatable = false, nullable = false)
    private Long id;
    @Column(name = "post_title", nullable = false, length = 32)
    private String title;
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
    @Builder.Default //builder 시 초기화 무시되는 것을 방지
    private List<Comment> commentList=new ArrayList<>();

    public void updatePost(PostUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
    public void likesCountPlus() {
        this.likesCount++;
    }
    public void likesCountMinus() {
        this.likesCount--;
    }
}

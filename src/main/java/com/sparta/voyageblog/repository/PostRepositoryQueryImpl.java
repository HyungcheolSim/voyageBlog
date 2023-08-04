package com.sparta.voyageblog.repository;

import static com.sparta.voyageblog.entity.QPost.post;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.voyageblog.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
@Slf4j
public class PostRepositoryQueryImpl implements PostRepositoryQuery {

  private final JPAQueryFactory jpaQueryFactory;


  //내용에 keyword 가 포함되는 post 목록 (하위 댓글 목록은 최신순) 부터 내림차순 정렬 조회
  @Override
  public List<Post> searchContents(String keyword, Pageable pageable) {
    var posts = jpaQueryFactory.selectDistinct(post).from(post)
        .leftJoin(post.commentList)
        .fetchJoin()
        .where(post.contents.contains(keyword))

        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .orderBy(post.commentList.any().createdAt.desc()).fetch();

    long totalSize = countQuery(keyword).fetch().get(0);
    log.info("검색된 총 게시물 수 : " + totalSize);
/*
    threads.stream()
        .map(Thread::getComments)
        .forEach(comments -> comments
            .forEach(comment -> Hibernate.initialize(comment.getEmotions())));
*/

    return PageableExecutionUtils.getPage(posts, pageable, () -> totalSize).toList();
  }

  private JPAQuery<Long> countQuery(String keyword) {
    return jpaQueryFactory.select(Wildcard.count)
        .from(post)
        .where(post.contents.contains(keyword));
  }
}

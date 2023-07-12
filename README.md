# voyageBlog
spring 개인과제 나만의 블로그 만들기

프로젝트 설명 & API 명세서 & 개발 및 리팩토링 과정
--------------------
https://boundless-pudding-4e9.notion.site/189b7be021e5442993234dcbb0d185e3?pvs=4

----------------------
## 진행상황
- [ ]  예외처리
## 리팩토링
- [ ]  cascade →post update, delete에 적용
- [ ]  로그 디테일하게 남기기
- [ ]  access token
- [ ]  refresh token
- [ ]  swagger 적용
- [ ]  readme 수정
------------------------------

## API 명세서
https://documenter.getpostman.com/view/27930567/2s93z86NDq

<details>
<summary>최종 UML</summary>
<div markdown="1">
    <img src="https://i.postimg.cc/rpbDGqqQ/voyage-Blog-usecase-Diagram-lv3.png" height="700">
</div>
</details>
<details>
<summary>최종 ERD</summary>
<div markdown="2">

<img src="https://i.postimg.cc/BQMKbKGk/lv3-final-erd.png" height="700">

</div>
</details>
<details>
<summary>최종 DDL</summary>
<div markdown="2">

```
create table if not exists user
(
    u_id       bigint auto_increment
        primary key,
    u_email    varchar(36)            not null,
    u_password varchar(64)            not null,
    u_role     enum ('ADMIN', 'USER') not null,
    u_username varchar(12)            not null,
    constraint UK_3oypjjd5orxmgq581pe1rj5q2
        unique (u_username)
);

create table if not exists post
(
    p_id               bigint auto_increment
        primary key,
    created_date       datetime(6) null,
    last_modified_date datetime(6) null,
    p_contents         text        not null,
    p_title            varchar(32) not null,
    p_username         varchar(12) not null,
    u_id               bigint      not null,
    constraint FKfvid82cuoi8ffelpry6l2cgxb
        foreign key (u_id) references user (u_id)
);

create table if not exists comment
(
    c_id               bigint auto_increment
        primary key,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    c_contents         varchar(255) not null,
    c_username         varchar(12)  not null,
    p_id               bigint       not null,
    u_id               bigint       not null,
    constraint FKk27qfspr5ar25vidcm2vrisna
        foreign key (p_id) references post (p_id),
    constraint FKoaey9dnt06ts7qqvkknv7ym2n
        foreign key (u_id) references user (u_id)
);

```

</div>
</details>

소감
==
>SpringBoot, JPA가 친숙해졌다. 부족한 점이 많지만 피드백과 리팩토링을 통해 계속 코드와 내 역량을 향상시키고 싶다. <br>
>API 명세서는 정리한다고 정리했는데 난잡해보인다. Swagger 추가까지 해볼 수 있을까?<br>
>Spring Security 와 Jwt는 완전히 이해한 상태는 아니지만 어느정도 실행과정 정도는 이해한 느낌으로 수행했다.<br>
>9시 이후에도 조금 더 시간을 투자하며 만들었고, Comment에서 복합키를 사용이 맞다고 판단해 복합키 관련 공부를하며<br> 
>@IdClass, @EmbeddedId 등에 대해서도 알 수 있었고 API와 클래스간 변환에 익숙해지는 계기가 됐다. <br>
>또한 아주 간단한 Stream을 사용해보며 계속 눈에 익히고 있다.. 언젠가는 자유자재로 쓰는 그날까지..<br>




package com.sparta.voyageblog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.voyageblog.dto.SignupRequestDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Post Controller Test")
class PostControllerTest {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  MockMvc mvc;

  @Autowired
  private WebApplicationContext ctx;

  private static final String BASE_URL = "/api/v1";


  @BeforeAll
  public void setup() {
    this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
        .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
        .alwaysDo(print())
        .build();
  }

  /*  @Test
    @DisplayName("게시글 저장 테스트")
    void savePost() throws Exception {
      //given
      String title = "게시글 저장 테스트 제목";
      String content = "게시글 저장 테스트 내용";

      //when
      String body = objectMapper.writeValueAsString(
          new PostRequestDto(title, content)
      );
      //then
      mvc.perform(post(BASE_URL + "/posts")
              .content(body) //HTTP Body에 데이터를 담는다
              .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
          )
          .andExpect(status().isOk())
          .andExpect(content().string("1"));

    }*/
  @Test
  @DisplayName("회원가입 테스트")
  void savePost() throws Exception {

    //given
    String username = "testuser3";
    String password = "123123123";
    String email = "testuser3@gmail.com";
    boolean admin = false;
    String adminToken = "";

    //when
    String body = objectMapper.writeValueAsString(
        new SignupRequestDto(username, password, email, admin, adminToken)
    );
    //then
    mvc.perform(post("/api/auth/signup")
            .content(body) //HTTP Body에 데이터를 담는다
            .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
        )
        .andExpect(status().isCreated())
        .andReturn();

  }


}
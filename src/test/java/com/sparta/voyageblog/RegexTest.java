package com.sparta.voyageblog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("정규표현식 테스트")
public class RegexTest {

    @Test
    @DisplayName("비밀번호 정규표현식 테스트 8~15글자 영숫자")
    void passwordTest() {
        String patter="^[A-Za-z0-9+]{8,15}";
        String input="sim341212123";
        Assertions.assertTrue(input.matches(patter));
    }

}

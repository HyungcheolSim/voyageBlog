package com.sparta.voyageblog.dto;

import com.sparta.voyageblog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//아직 사용되지 않는다.
public class UserResponseDto {
    private Long id;
    private String email;

    public UserResponseDto(User user) {
        this.id=user.getId();
        this.email= user.getEmail();
    }
}

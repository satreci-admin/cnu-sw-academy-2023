package com.cnu.simple.member;

import com.cnu.simple.robot.Robot;
import com.cnu.simple.work.WorkSpecification;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
    private String password;

    @Builder
    public MemberRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}

package com.cnu.simple.member;

import com.cnu.simple.robot.Robot;
import com.cnu.simple.work.WorkSpecification;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
    private String password;


    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
    }
}

package com.cnu.simple.member;

import com.cnu.simple.robot.Robot;
import com.cnu.simple.work.WorkSpecification;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {

    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<WorkSpecification> workSpecifications;

    @Builder
    public MemberRequestDto(UUID id, String name, String email, String password, List<WorkSpecification> workSpecifications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.workSpecifications = workSpecifications;
    }

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .workSpecification(this.workSpecifications)
                .build();
    }
}

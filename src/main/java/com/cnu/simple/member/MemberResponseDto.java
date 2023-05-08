package com.cnu.simple.member;

import com.cnu.simple.work.WorkSpecification;
import lombok.*;

import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private List<WorkSpecification> workSpecifications;
}

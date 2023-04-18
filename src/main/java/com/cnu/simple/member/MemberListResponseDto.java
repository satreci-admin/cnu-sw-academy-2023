package com.cnu.simple.member;

import com.cnu.simple.work.WorkSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberListResponseDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<WorkSpecification> workSpecifications;
}

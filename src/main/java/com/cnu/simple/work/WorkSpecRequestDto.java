package com.cnu.simple.work;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpecRequestDto {
    private String name;
    private String memo;
    private Schedule schedule;
    private String script;
    private UUID memberId;
}

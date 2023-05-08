package com.cnu.simple.work;

import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpecRequestDto {
    private String name;
    private String memo;
    private String script;
    private UUID memberId;
}

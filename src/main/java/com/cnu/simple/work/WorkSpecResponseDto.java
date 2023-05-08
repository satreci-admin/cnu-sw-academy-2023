package com.cnu.simple.work;

import lombok.*;

import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpecResponseDto {
    private Long id;
    private String name;
    private String script;
    private String memo;
}

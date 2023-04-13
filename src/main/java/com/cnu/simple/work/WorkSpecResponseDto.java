package com.cnu.simple.work;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpecResponseDto {
    private Long id;
    private String name;
    private Schedule schedule;
    private String script;
    private String memo;
}

package com.cnu.simple.robot;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RobotResponseDto {
    private String name;
    private String sshId;
    private String sshPw;
    private int port;
    private String ip;
}

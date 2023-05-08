package com.cnu.simple.robot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RobotRequestDto {
    private String name;
    private String sshId;
    private String sshPw;
    private int port;
    private String ip;

    public Robot toEntity() {
        return Robot.builder()
                .name(name)
                .sshId(sshId)
                .sshPw(sshPw)
                .port(port)
                .ip(ip)
                .build();
    }
}

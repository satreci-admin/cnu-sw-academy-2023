package com.cnu.simple.robot;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RobotDto {
    private Long id;
    private String name;
    private String sshId;
    private String sshPw;
    private int port;
    private String ip;
    private String type;

    public Robot toEntity() {
        return Robot.builder()
                .id(id)
                .name(name)
                .sshId(sshId)
                .sshPw(sshPw)
                .port(port)
                .ip(ip)
                .type(type)
                .build();
    }

    public RobotDto(Robot robot) {
        this.id = robot.getId();
        this.name = robot.getName();
        this.sshId = robot.getSshId();
        this.sshPw = robot.getSshPw();
        this.port = robot.getPort();
        this.ip = robot.getIp();
        this.type = robot.getType();
    }
}

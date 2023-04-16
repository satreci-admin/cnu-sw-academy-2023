package com.cnu.simple.robot;

import com.cnu.simple.work.WorkSpecification;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class RobotRequest {
    private String name;

    private String sshId;

    private String sshPw;

    private int port;

    private List<WorkSpecification> workSpecifications;
    private String ip;
    private String type;

    public Robot toEntity() {
        return Robot.builder()
                .name(name)
                .sshId(sshId)
                .sshPw(sshPw)
                .port(port)
                .ip(ip)
                .type(type)
                .build();
    }
}

package com.cnu.simple.robot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String type;
}

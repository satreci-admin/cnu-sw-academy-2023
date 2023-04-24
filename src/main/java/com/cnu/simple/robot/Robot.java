package com.cnu.simple.robot;

import com.cnu.simple.work.WorkSpecification;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Table(name = "robots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "SSH_Id")
    private String sshId;

    @Column(name = "SSH_PW")
    private String sshPw;

    @Column(name = "port")
    private int port;

    @OneToMany(mappedBy = "robot")
    private List<WorkSpecification> workSpecifications;

    @Column(name = "IP")
    private String ip;

    @Column(name = "type")
    private String type;

    @Builder
    public Robot(Long id,String name, String sshId, String sshPw, int port, String ip, String type) {
        this.id = id;
        this.name = name;
        this.sshId = sshId;
        this.sshPw = sshPw;
        this.port = port;
        this.ip = ip;
        this.type = type;
    }
}

package com.cnu.simple.robot;

import com.cnu.simple.work.WorkSpecification;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

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
    @Setter
    private String name;

    @Column(name = "SSH_Id")
    @Setter
    private String sshId;

    @Column(name = "SSH_PW")
    @Setter
    private String sshPw;

    @Column(name = "port")
    @Setter
    private int port;

    @OneToMany
    @JoinColumn(name = "work_spec_id", referencedColumnName = "id")
    private List<WorkSpecification> workSpecifications;

    @Column(name = "IP")
    @Setter
    private String ip;

    @Column(name = "type")
    @Setter
    private String type;

    @Builder
    public Robot(String name, String sshId, String sshPw, int port, String ip, String type) {
        this.name = name;
        this.sshId = sshId;
        this.sshPw = sshPw;
        this.port = port;
        this.ip = ip;
        this.type = type;
    }
}

package com.cnu.simple.robot;

import com.cnu.simple.work.WorkSpecification;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Entity
@Table(name = "robots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "SSH_Id")
    private String sshId;

    @Column(name = "SSH_PW")
    private String sshPw;

    @Column(name = "port")
    private int port;

    @Column(name = "IP")
    private String ip;
}

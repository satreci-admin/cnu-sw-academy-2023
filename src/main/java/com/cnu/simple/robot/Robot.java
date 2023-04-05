package com.cnu.simple.robot;

import com.cnu.simple.work.WorkSpecification;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "robots")
public class Robot {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "SSH_Id")
    private String sshId;

    @Column(name = "SSH_PW")
    private String sshPw;

    @Column(name = "port")
    private int port;

    @ManyToOne
    @JoinColumn(name = "work_spec_id", referencedColumnName = "id")
    private WorkSpecification workSpecification;

    @Column(name = "IP")
    private String ip;

    @Column(name = "memo")
    private String memo;
}

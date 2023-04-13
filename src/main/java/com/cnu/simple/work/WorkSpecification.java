package com.cnu.simple.work;

import com.cnu.simple.member.Member;
import com.cnu.simple.robot.Robot;
import com.cnu.simple.work.deprecated.TaskDetails;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
public class WorkSpecification {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "schedule", nullable = false)
    private String schedule;

    @Column(name = "memo", nullable = true)
    private String memo;

    @ManyToOne
    @JoinColumn(name = "robot_id", referencedColumnName = "id")
    private Robot robot;

    @OneToMany(mappedBy = "workSpecification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskDetails> taskDetails;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;


}

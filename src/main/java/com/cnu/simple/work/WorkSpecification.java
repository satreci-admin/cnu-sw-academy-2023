package com.cnu.simple.work;

import com.cnu.simple.member.Member;
import com.cnu.simple.robot.Robot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Builder
@Getter
@Entity
@Table(name = "work_specification")
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "schedule", nullable = false)
    private String schedule;

    @Column(name = "memo", nullable = true)
    private String memo;

    @Column(name="script", nullable = false)
    private String script;

    @ManyToOne
    @JoinColumn(name = "robot_id", referencedColumnName = "id")
    private Robot robot;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

}

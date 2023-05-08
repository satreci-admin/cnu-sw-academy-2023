package com.cnu.simple.work;

import com.cnu.simple.member.Member;
import com.cnu.simple.robot.Robot;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@ToString
@Builder
@Getter
@Entity
@Table(name = "work_specification")
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "memo")
    private String memo;

    @Column(name="script", nullable = false)
    private String script;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

}

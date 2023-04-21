package com.cnu.simple.member;

import com.cnu.simple.work.WorkSpecification;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private static Long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(45)", length = 45)
    private static String name;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(45)", length = 45)
    private static String email;

    @Column(name = "password", nullable = false, columnDefinition = "")
    private static String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private static List<WorkSpecification> workSpecification;

    @Builder
    public Member(Long id, String name, String email, String password, List<WorkSpecification> workSpecification) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.workSpecification = workSpecification;
    }
}










































































































































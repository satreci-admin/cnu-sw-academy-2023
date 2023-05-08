package com.cnu.simple.member;

import com.cnu.simple.work.WorkSpecification;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(45)", length = 45)
    private String name;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(45)", length = 45)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "")
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<WorkSpecification> workSpecification;
}

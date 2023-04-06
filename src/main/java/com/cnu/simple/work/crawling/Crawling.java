package com.cnu.simple.work.crawling;

import com.cnu.simple.work.TaskDetails;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@DiscriminatorValue("CRAWLING")
public class Crawling extends TaskDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String keyword;

    @Column(name = "newsSite", columnDefinition = "TEXT", nullable = false)
    private NewsSite newsSite;

    @Column(name = "sendEmail", columnDefinition = "VARCHAR(45)", nullable = false, length = 45)
    private String sendEmail;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ReceiveEmail>  receiveEmails;

    @Column(name = "newsCount", columnDefinition = "INT(10)")
    private int newsCount;


}
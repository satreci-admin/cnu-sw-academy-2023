package com.cnu.simple.work.crawling;

import com.cnu.simple.work.TaskDetails;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@DiscriminatorValue("CRAWLING")
public class Crawling extends TaskDetails {

}

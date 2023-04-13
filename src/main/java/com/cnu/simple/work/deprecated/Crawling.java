package com.cnu.simple.work.deprecated;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("CRAWLING")
public class Crawling extends TaskDetails {

}

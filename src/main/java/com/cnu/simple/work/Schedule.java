package com.cnu.simple.work;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private String startDate;
    private String startTime;
    private Number repeatDay;

    protected String getCrone(){
        return null;
    }

    public static Schedule getScheduleFromCrone(String crone){
        return null;
    }
}

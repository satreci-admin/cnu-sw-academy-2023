package com.cnu.simple.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RobotNotFoundException extends RuntimeException{

    public RobotNotFoundException(String msg){
        super(msg);
    }

    public RobotNotFoundException(Exception e){
        super(e);
    }
}

package com.cnu.simple.exception;

public class WorkSpecificationNotFoundException extends RuntimeException{
    public WorkSpecificationNotFoundException(String msg){
        super(msg);
    }
    public WorkSpecificationNotFoundException(Exception e){
        super(e);
    }
}

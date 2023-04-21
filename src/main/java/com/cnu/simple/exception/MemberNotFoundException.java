package com.cnu.simple.exception;

import com.cnu.simple.member.Member;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(String msg){
        super(msg);
    }

    public MemberNotFoundException(Exception e){
        super(e);
    }
}

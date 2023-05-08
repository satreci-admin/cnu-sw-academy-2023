package com.cnu.simple.exception;

public class MemberVaildateDuplicateException extends Exception{
    public MemberVaildateDuplicateException(String msg) {
        super(msg);
    }

    public MemberVaildateDuplicateException(Exception e){
        super(e);
    }
}

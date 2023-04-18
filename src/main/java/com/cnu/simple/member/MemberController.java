package com.cnu.simple.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("")
    public ResponseEntity<Member> addMember(@RequestBody MemberRequestDto memberRequestDto) {
        Member addMember = memberService.save(memberRequestDto);
        return new ResponseEntity<>(addMember, HttpStatus.CREATED);
    }



}


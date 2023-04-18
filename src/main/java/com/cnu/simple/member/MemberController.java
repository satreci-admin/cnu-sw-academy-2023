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

    @GetMapping()
    public ResponseEntity<List<Member>> listMember(@PathVariable Long id) {
        List<Member> listMember = memberService.findMember();
        Optional<Member> oneMember = memberService.findOne(id);
        return ResponseEntity.ok(listMember);
    }

    @PutMapping()
    public ResponseEntity<Member> updateMember(@PathVariable Long id,
                                               @RequestBody MemberRequestDto memberRequestDto) {
        Member updateMember = memberService.updateMember(id, memberRequestDto);
        return ResponseEntity.ok(updateMember);
    }

    @DeleteMapping()
    public ResponseEntity<Member> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}


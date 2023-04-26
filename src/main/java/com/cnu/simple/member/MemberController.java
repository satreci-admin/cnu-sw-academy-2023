package com.cnu.simple.member;

import com.cnu.simple.exception.MemberVaildateDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/member")
    public MemberResponseDto addMember(@RequestBody MemberRequestDto memberRequestDto) throws MemberVaildateDuplicateException {
        return memberService.saveMember(memberRequestDto);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> listMember(@PageableDefault(size = 5) Pageable pageable) {
        List<Member> listMember = memberService.findMember(pageable);
        return ResponseEntity.ok(listMember);
    }

    @GetMapping("/member/{id}")
    public MemberResponseDto oneMember(@PathVariable UUID id){
        MemberResponseDto oneMember = memberService.findOne(id);
        return memberService.findOne(oneMember.getId());

    }

    @PutMapping("/member/{id}")
    public Optional<MemberResponseDto> updateMember(@PathVariable Long id,
                                                    @RequestBody MemberRequestDto memberRequestDto) {
        Optional<MemberResponseDto> updateMember = memberService.updateMember(id, memberRequestDto);
        return memberService.updateMember(id, memberRequestDto);
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
package com.cnu.simple.member;

import com.cnu.simple.exception.MemberVaildateDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Member>> listMember(@PageableDefault(size = 5) Pageable pageable) {
        Page<Member> listMember = memberService.findMember(pageable);
        return ResponseEntity.ok(listMember);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberResponseDto> oneMember(@PathVariable("id") UUID id){
        Optional<MemberResponseDto> memberResponseDto = memberService.findOne(id);
        return memberResponseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/member/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable("id") UUID id,
                                                          @RequestBody MemberRequestDto memberRequestDto) {
        Optional<MemberResponseDto> modify = memberService.updateMember(id, memberRequestDto);
        if(modify.isPresent()){
            return ResponseEntity.ok(modify.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable("id") UUID id) {
        memberService.deleteMember(id);
    }
}
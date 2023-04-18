package com.cnu.simple.member;

import com.cnu.simple.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public Member save(MemberRequestDto memberRequestDto) {
        vaildateDuplicateMember(memberRequestDto);

        MemberRequestDto member = MemberRequestDto.builder()
                .id(memberRequestDto.getId())
                .name(memberRequestDto.getName())
                .email(memberRequestDto.getEmail())
                .password(memberRequestDto.getPassword())
                .workSpecifications(memberRequestDto.getWorkSpecifications())
                .build();

        return memberRepository.save(memberRequestDto.toEntity());
    }

    private void vaildateDuplicateMember(MemberRequestDto memberRequestDto) {
        memberRepository.findById(memberRequestDto.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }


}












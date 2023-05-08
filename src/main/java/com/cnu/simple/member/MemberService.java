package com.cnu.simple.member;

import com.cnu.simple.exception.MemberNotFoundException;
import com.cnu.simple.exception.MemberVaildateDuplicateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private MemberResponseDto convertEntityToDto(Object entity){
        MemberResponseDto response = new MemberResponseDto();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) throws MemberVaildateDuplicateException {
        vaildateDuplicateMember(memberRequestDto.getEmail());
        Member saved = memberRepository.save(memberRequestDto.toEntity());
        return convertEntityToDto(saved);
    }

    private void vaildateDuplicateMember(String email) throws MemberVaildateDuplicateException {
      Member findMember = memberRepository.findByEmail(email);
        if(findMember != null) {
            throw new MemberVaildateDuplicateException("이미 존재하는 회원입니다.");
        }
    }

    public Page<Member> findMember(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public Optional<MemberResponseDto> findOne(UUID id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("회원 ID " + id + "에 해당하는 로봇을 찾을 수 없습니다.")
        );

        return Optional.of(convertEntityToDto(member));

    }

    public Optional<MemberResponseDto> updateMember(UUID id, MemberRequestDto memberRequestDto) {
        Member saved = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return Optional.of(convertEntityToDto(saved));
    }



    public void deleteMember(UUID id) {
        memberRepository.deleteById(id);
    }
}
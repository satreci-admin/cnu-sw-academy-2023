package com.cnu.simple.member;

import com.cnu.simple.exception.MemberNotFoundException;
import com.cnu.simple.exception.MemberVaildateDuplicateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public MemberResponseDto saveMember(MemberRequestDto memberRequestDto) {
        Member saved = memberRepository.save(memberRequestDto.toEntity());
        return convertEntityToDto(saved);
    }

//    private void vaildateDuplicateMember(MemberRequestDto memberRequestDto) throws MemberVaildateDuplicateException {
//      Member findMember = memberRepository.findByEmail(memberRequestDto.getEmail());
//        if(findMember != null) {
//            throw new MemberVaildateDuplicateException("이미 존재하는 회원입니다.");
//        }
//    }

    public List<Member> findMember(Pageable pageable) {
        return memberRepository.findAll();
    }

    public MemberResponseDto findOne(UUID id) {
       Object found = memberRepository.findById(id);

       return convertEntityToDto(found);

    }

    public Optional<MemberResponseDto> updateMember(Long id, MemberRequestDto memberRequestDto) {
        return memberRepository.findById(id)
                .map(member -> {
                    memberRequestDto.getName();
                    memberRequestDto.getEmail();
                    memberRequestDto.getPassword();
                    memberRequestDto.getWorkSpecifications();

                    return convertEntityToDto(member);

                });
    }



    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }


}
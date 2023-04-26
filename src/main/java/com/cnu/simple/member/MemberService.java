package com.cnu.simple.member;

import com.cnu.simple.exception.MemberNotFoundException;
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

    public Optional<MemberResponseDto> findOne(UUID id) {
       Member member = memberRepository.findById(id).orElseThrow(
               () -> new MemberNotFoundException("회원 ID " + id + "에 해당하는 로봇을 찾을 수 없습니다.")
       );

       return Optional.of(convertEntityToDto(member));

    }

    public Optional<MemberResponseDto> updateMember(UUID id, MemberRequestDto memberRequestDto) {
        Member saved = memberRepository.save(
                Member.builder()
                        .name(memberRequestDto.getName())
                        .email(memberRequestDto.getEmail())
                        .password(memberRequestDto.getPassword())
                        .workSpecification(memberRequestDto.getWorkSpecifications())
                        .build()

        );
        return Optional.of(convertEntityToDto(saved));
    }



    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
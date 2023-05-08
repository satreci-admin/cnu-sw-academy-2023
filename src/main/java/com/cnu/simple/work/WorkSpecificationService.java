package com.cnu.simple.work;

import com.cnu.simple.exception.MemberNotFoundException;
import com.cnu.simple.exception.WorkSpecificationNotFoundException;
import com.cnu.simple.member.Member;
import com.cnu.simple.member.MemberRepository;
import com.cnu.simple.robot.RobotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkSpecificationService {

    private final WorkSpecificationRepository workRepository;
    private final MemberRepository memberRepository;
    private final RobotRepository robotRepository;

    private WorkSpecResponseDto convertEntityToDto(WorkSpecification entity){
        WorkSpecResponseDto response = new WorkSpecResponseDto();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public WorkSpecResponseDto addWorkSpec(WorkSpecRequestDto workSpecRequestDto) {
        Member member = memberRepository.findById(workSpecRequestDto.getMemberId()).orElseThrow(MemberNotFoundException::new);
        WorkSpecification workSpecification = WorkSpecification.builder()
                .name(workSpecRequestDto.getName())
                .memo(workSpecRequestDto.getMemo())
                .script(workSpecRequestDto.getScript())
                .member(member)
                .build();
        WorkSpecification saved = workRepository.save(workSpecification);
        log.info("saved workSpec -> {}", saved);
        return convertEntityToDto(saved);
    }

    public WorkSpecResponseDto getWorkSpec(Long id) {
        WorkSpecification found = workRepository.findById(id).orElseThrow(
                () -> new WorkSpecificationNotFoundException("작업번호 " + id + "에 해당하는 작업명세서를 찾을 수 없습니다.")
        );

        return convertEntityToDto(found);
    }

    public List<WorkSpecResponseDto> getAllWorkSpec() {
        return workRepository.findAll().stream().map(
                this::convertEntityToDto
        ).collect(Collectors.toList());
    }

    public WorkSpecResponseDto modifyWorkSpec(Long id, WorkSpecRequestDto workSpecRequestDto) {
        WorkSpecification found = workRepository.findById(id).orElseThrow(
                () -> new WorkSpecificationNotFoundException("작업번호 " + id + "에 해당하는 작업명세서를 찾을 수 없습니다.")
        );

        WorkSpecification saved = workRepository.save(
                WorkSpecification.builder()
                        .id(id)
                        .name(workSpecRequestDto.getName())
                        .memo(workSpecRequestDto.getMemo())
                        .member(found.getMember())
                        .script(workSpecRequestDto.getScript())
                        .build()
        );
        return convertEntityToDto(saved);
    }

    public void removeWorkSpec(Long id) {
        workRepository.deleteById(id);
    }


    public List<WorkSpecResponseDto> getWorkSpecByMemberId(UUID memberId) {
        return workRepository.findAllByMemberId(memberId).stream().map(
                this::convertEntityToDto
        ).collect(Collectors.toList());
    }
}

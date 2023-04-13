package com.cnu.simple.work;

import com.cnu.simple.exception.WorkSpecificationNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkSpecificationService {

    @Autowired
    private WorkSpecificationRepository workRepository;

//    @Autowired
//    private MemberRepository memberRepository;
    private WorkSpecResponseDto convertEntityToDto(WorkSpecification entity){
        WorkSpecResponseDto response = new WorkSpecResponseDto();
        BeanUtils.copyProperties(entity, response, "schedule");
        response.setSchedule(Schedule.getScheduleFromCrone(entity.getSchedule()));
        return response;
    }

    public WorkSpecResponseDto addWorkSpec(WorkSpecRequestDto workSpecRequestDto) {
        WorkSpecification workSpecification = WorkSpecification.builder()
                .name(workSpecRequestDto.getName())
                .memo(workSpecRequestDto.getMemo())
                .schedule(workSpecRequestDto.getSchedule().getCrone())
                /**
                 * 멤버, 로봇 설정 부분 추가해야 함
                 * */
                .build();
        WorkSpecification saved = workRepository.save(workSpecification);
//        로봇 설정 부분 추가
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
                        .schedule(workSpecRequestDto.getSchedule().getCrone())
                        .member(found.getMember())
                        .robot(found.getRobot())
                        .build()
        );
        return convertEntityToDto(saved);
    }

    public void removeWorkSpec(Long id) {
        workRepository.deleteById(id);
    }
}

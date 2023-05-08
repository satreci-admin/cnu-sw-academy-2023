package com.cnu.simple.robot;

import com.cnu.simple.exception.RobotNotFoundException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RobotService {
    private final RobotRepository robotRepository;

    private RobotResponseDto convertEntityToDto(Robot entity){
        RobotResponseDto response = new RobotResponseDto();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public RobotResponseDto createRobot(RobotRequestDto robotRequestDto) {
        Robot robot = robotRepository.save(robotRequestDto.toEntity());
        return convertEntityToDto(robot);
    }

    public List<RobotResponseDto> getRobots() {
        List<Robot> robots = robotRepository.findAll();
        return robots.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<RobotResponseDto> getRobot(Long robotId) {
        Robot robot = robotRepository.findById(robotId).orElseThrow(
                        () -> new RobotNotFoundException("로봇 ID  " + robotId + "에 해당하는 로봇을 찾을 수 없습니다.")
                );
        return Optional.of(convertEntityToDto(robot));
    }

    public Optional<RobotResponseDto> updateRobot(Long robotId, RobotRequestDto robotRequestDto) {
        Robot robot = robotRepository.findById(robotId).orElseThrow(
                () -> new RobotNotFoundException("로봇 ID  " + robotId + "에 해당하는 로봇을 찾을 수 없습니다.")
        );

        Robot saved = robotRepository.save(
                Robot.builder()
                        .id(robotId)
                        .name(robotRequestDto.getName())
                        .ip(robotRequestDto.getIp())
                        .port(robotRequestDto.getPort())
                        .sshId(robotRequestDto.getSshId())
                        .sshPw(robotRequestDto.getSshPw())
                        .build()
        );
        return Optional.of(convertEntityToDto(saved));
    }

    public void deleteRobot(Long robotId) {
        robotRepository.findById(robotId)
                .ifPresent(robotRepository::delete);
    }

    public boolean testSshConnection(RobotRequestDto robotRequestDto) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(robotRequestDto.getSshId(), robotRequestDto.getIp(), robotRequestDto.getPort());
            session.setPassword(robotRequestDto.getSshPw());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            return true;
        } catch (JSchException e) {
            return false;
        } finally {
            if (session != null) {
                session.disconnect();
            }
        }
    }
}

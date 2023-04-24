package com.cnu.simple.robot;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RobotService {
    private final RobotRepository robotRepository;

    public RobotDto createRobot(RobotDto robotDto) {
        robotRepository.save(robotDto.toEntity());
        return robotDto;
    }

    public List<RobotDto> getRobots() {
        List<Robot> robots = robotRepository.findAll();
        return robots.stream()
                .map(RobotDto::new)
                .collect(Collectors.toList());
    }

    public Optional<RobotDto> getRobot(Integer robotId) {
        return robotRepository.findById(robotId)
                .map(RobotDto::new);
    }

    public Optional<RobotDto> updateRobot(Integer robotId, RobotDto robotDto) {
        return robotRepository.findById(robotId)
                .map(robot -> {
                    Robot updatedRobot = robotRepository.save(robot);
                    return new RobotDto(updatedRobot);
                });
    }

    public void deleteRobot(Integer robotId) {
        robotRepository.findById(robotId)
                .ifPresent(robotRepository::delete);
    }

    public boolean testSshConnection(RobotDto robotDto) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(robotDto.getSshId(), robotDto.getIp(), robotDto.getPort());
            session.setPassword(robotDto.getSshPw());
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

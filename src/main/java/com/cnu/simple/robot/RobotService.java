package com.cnu.simple.robot;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RobotService {
    private final RobotRepository robotRepository;

    public Robot createRobot(RobotRequest robotRequest) {
        return robotRepository.save(robotRequest.toEntity());
    }

    public List<Robot> getRobots() {
        return robotRepository.findAll();
    }

    public Optional<Robot> getRobot(Integer RobotId) {
        return robotRepository.findById(RobotId);
    }

    public Optional<Robot> updateRobot(Integer robotId, RobotRequest robotRequest) {
        return robotRepository.findById(robotId)
                .map(robot -> {
                    robot.setName(robotRequest.getName());
                    robot.setSshId(robotRequest.getSshId());
                    robot.setSshPw(robotRequest.getSshPw());
                    robot.setPort(robotRequest.getPort());
                    robot.setIp(robotRequest.getIp());
                    robot.setType(robotRequest.getType());

                    return robotRepository.save(robot);
                });
    }

    public void deleteRobot(Integer RobotId) {
        robotRepository.findById(RobotId)
                .ifPresent(robotRepository::delete);
    }

    public boolean testSshConnection(RobotRequest robotRequest) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(robotRequest.getSshId(), robotRequest.getIp(), robotRequest.getPort());
            session.setPassword(robotRequest.getSshPw());
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

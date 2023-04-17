package com.cnu.simple.robot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/robot")
@RequiredArgsConstructor
public class RobotController {
    private final RobotService robotService;

    @PostMapping
    public ResponseEntity<Robot> createRobot(@RequestBody RobotRequest robotRequest) {
        Robot createdRobot = robotService.createRobot(robotRequest);
        return new ResponseEntity<>(createdRobot, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Robot>> getRobots() {
        List<Robot> robots = robotService.getRobots();
        return ResponseEntity.ok(robots);
    }

    @GetMapping("/{robotId}")
    public ResponseEntity<Robot> getRobot(@PathVariable("robotId") Integer robotId) {
        Optional<Robot> robot = robotService.getRobot(robotId);
        if (robot.isPresent()) {
            return ResponseEntity.ok(robot.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{robotId}")
    public ResponseEntity<Robot> updateRobot(@PathVariable("robotId")Integer robotId,
                                             @RequestBody RobotRequest robotRequest) {
        Optional<Robot> updatedRobot = robotService.updateRobot(robotId, robotRequest);
        if (updatedRobot.isPresent()) {
            return ResponseEntity.ok(updatedRobot.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{robotId}")
    public ResponseEntity<Void> deleteRobot(@PathVariable("robotId") Integer robotId) {
        robotService.deleteRobot(robotId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/test/connection")
    public ResponseEntity<Boolean> connectRobot(@RequestBody RobotRequest robotRequest) {
        boolean isSuccess = robotService.testSshConnection(robotRequest);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}

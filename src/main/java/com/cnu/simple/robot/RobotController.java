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
    public ResponseEntity<RobotDto> createRobot(@RequestBody RobotDto robotDto) {
        RobotDto createdRobot = robotService.createRobot(robotDto);
        return new ResponseEntity<>(createdRobot, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RobotDto>> getRobots() {
        List<RobotDto> robotDtos = robotService.getRobots();
        return ResponseEntity.ok(robotDtos);
    }

    @GetMapping("/{robotId}")
    public ResponseEntity<RobotDto> getRobot(@PathVariable("robotId") Integer robotId) {
        Optional<RobotDto> RobotRequest = robotService.getRobot(robotId);
        if (RobotRequest.isPresent()) {
            return ResponseEntity.ok(RobotRequest.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{robotId}")
    public ResponseEntity<RobotDto> updateRobot(@PathVariable("robotId")Integer robotId,
                                                @RequestBody RobotDto robotDto) {
        Optional<RobotDto> updatedRobotRequest = robotService.updateRobot(robotId, robotDto);
        if (updatedRobotRequest.isPresent()) {
            return ResponseEntity.ok(updatedRobotRequest.get());
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
    public ResponseEntity<Boolean> connectRobot(@RequestBody RobotDto robotDto) {
        boolean isSuccess = robotService.testSshConnection(robotDto);
        if (isSuccess) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
